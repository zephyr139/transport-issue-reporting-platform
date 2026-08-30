package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos.response;

public record StopResponseDTO(
        long id,
        String name,
        int sequenceNumber
) {
}
