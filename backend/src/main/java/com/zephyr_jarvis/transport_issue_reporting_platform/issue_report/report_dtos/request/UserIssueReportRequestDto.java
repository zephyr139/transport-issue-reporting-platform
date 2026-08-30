package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.Nullable;

public record UserIssueReportRequestDto(
    @Nullable
    @Size(min = 3, max = 60)
    String title,

    @Nullable
    @Size(max = 250)
    String description,

    @NotBlank
    Long categoryId,

    @NotBlank
    Long stationId,

    @NotBlank
    Long nextStationId // TODO: verify if the nextStation is valid

//    @Nullable
//    Timestamp approximateOccurrence

) {

}
