package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos.response;

import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.enums.IssueReportStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record IssueReportResponseDto(
        long id,
        String title,
        String description,
        CategoryResponseDTO category,
        RouteResponseDTO route,
        StopResponseDTO stop,
        BigDecimal latitude,
        BigDecimal longitude,
        IssueReportStatus status,
        Timestamp createdAt
) {

}
