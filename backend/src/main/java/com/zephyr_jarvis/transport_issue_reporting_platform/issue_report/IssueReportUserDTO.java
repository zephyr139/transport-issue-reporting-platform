package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.Nullable;

public record IssueReportUserDTO(
    @Nullable
    @Size(min = 3, max = 60)
    String title,

    @Nullable
    @Size(max = 250)
    String description,

    @NotBlank
    Long categoryId,

    @NotBlank
    Long stopId,

    @Nullable
    Long routeId


//    @Nullable
//    Timestamp approximateOccurrence

) {

}
