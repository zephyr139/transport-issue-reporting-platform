package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos.response;

import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.enums.Circle;

public record RouteResponseDTO (
        long id,
        long number,
        Circle circle
){
}
