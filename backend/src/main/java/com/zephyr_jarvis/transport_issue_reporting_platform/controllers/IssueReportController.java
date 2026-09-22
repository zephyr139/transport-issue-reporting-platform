package com.zephyr_jarvis.transport_issue_reporting_platform.controllers;

import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.enums.ReportPriority;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.model.IssueReport;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.IssueReportService;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos.IssueReportFilterDto;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos.request.UserIssueReportRequestDto;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos.response.IssueReportResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/issue-reports")
@Tag(name = "Issue reports", description = "Endpoints for creating transport issue reports.")
public class IssueReportController {

    @Autowired
    IssueReportService issueReportService;

    @PostMapping("/create")
    @Operation(
            summary = "Create issue report",
            description = "Creates a transport issue report for a selected category and stop."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Issue report was created successfully."),
            @ApiResponse(responseCode = "400", description = "Request data is invalid.")
    })
    public IssueReportResponseDto issueReport(
            @RequestBody(
                    description = "Issue report details submitted by a user.",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UserIssueReportRequestDto.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "title": "Broken ticket machine",
                                      "description": "The card reader does not accept contactless payment.",
                                      "categoryId": 1,
                                      "stationId": 12,
                                      "nextStationId" : 13
                                    }
                                    """)
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody UserIssueReportRequestDto reportDto) {


        return issueReportService.create(reportDto);
    }

    @GetMapping("/list")
    @Operation(summary = "search and filter issue reports")
    public ResponseEntity<List<IssueReportResponseDto>> issueReportList(
            // Spring creates this record from query parameters such as
            // ?search=broken&status=SUBMITTED&stopId=5.
            IssueReportFilterDto filter,
            // Spring creates Pageable from page, size, and sort query parameters.
            // Defaults are used when the client does not send them:
            // ?page=0&size=20&sort=createdAt,desc
            @PageableDefault(sort = "createdAt", size = 20, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(issueReportService.searchReports(filter, pageable));
    }
}
