package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.repos;

import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.enums.ReportPriority;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.model.IssueReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// JpaRepository gives basic CRUD methods like save, findById, and findAll.
// JpaSpecificationExecutor adds findAll(Specification, Pageable), which is what
// lets the service combine dynamic filters with pagination and sorting.
public interface IssueReportRepo extends JpaRepository<IssueReport, Long>, JpaSpecificationExecutor<IssueReport> {

}
