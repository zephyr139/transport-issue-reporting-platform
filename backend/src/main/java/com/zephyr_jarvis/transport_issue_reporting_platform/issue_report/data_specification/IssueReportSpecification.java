package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.data_specification;

import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.model.IssueReport;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos.IssueReportFilterDto;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;



public class IssueReportSpecification {

    public static Specification<IssueReport> filter(IssueReportFilterDto filter) {
        // A Specification is a small object that Spring Data JPA turns into SQL.
        // root = IssueReport table/entity, query = current SQL query,
        // criteriaBuilder = helper for creating WHERE predicates.
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Pagination runs both a select query and a count query. Fetch joins
            // are useful for the select query because response mapping needs
            // category, route, and stop, but they should not be added to count.
            if (query.getResultType() != Long.class && query.getResultType() != long.class) {
                root.fetch("category", JoinType.LEFT);
                root.fetch("route", JoinType.LEFT);
                root.fetch("stop", JoinType.LEFT);
            }

            // Case-insensitive search over title and description.
            // SQL idea: lower(title) LIKE '%text%' OR lower(description) LIKE '%text%'.
            if (filter.search() != null && !filter.search().isBlank()) {
                String pattern = "%" + filter.search().trim().toLowerCase() + "%";
                Predicate titleMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),pattern);
                Predicate descMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")),pattern);
                predicates.add(criteriaBuilder.or(titleMatch,descMatch));
            }

//            Priority filter
            if (filter.priority() != null) {
                predicates.add(criteriaBuilder.equal(root.get("priority"), filter.priority()));
            }
//            Status filter
            if (filter.status() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.status()));
            }
//             Stop filter
            if (filter.stopId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("stop").get("id"), filter.stopId()));
            }
//            Date Range filter
            if (filter.fromDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), filter.fromDate()));
            }
            if (filter.dueDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dueDate"), filter.dueDate()));
            }

            // All active filters are combined with AND. If no filters were added,
            // this returns all issue reports and Pageable still controls sorting.
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
