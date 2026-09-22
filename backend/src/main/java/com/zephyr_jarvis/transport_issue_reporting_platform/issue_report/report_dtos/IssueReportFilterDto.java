package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos;

import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.enums.IssueReportStatus;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.enums.ReportPriority;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record IssueReportFilterDto(
        String search,
        ReportPriority priority,
        IssueReportStatus status,
        Long categoryId,
        Long stopId,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate
) {

}
