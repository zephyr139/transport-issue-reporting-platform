package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos;

import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.model.Category;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos.response.CategoryResponseDTO;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class IssueReportCategoryService {

    @Autowired
    public CategoryRepo repository;

    public CategoryResponseDTO createCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        repository.save(category);
        return new CategoryResponseDTO(category.getId(),category.getName());
    }
}
