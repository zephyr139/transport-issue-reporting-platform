package com.zephyr_jarvis.transport_issue_reporting_platform.controllers;

import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.model.IssueReport;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.IssueReportService;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.IssueReportUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Issue reports", description = "Endpoints for creating transport issue reports.")
public class IssueReportController {

    @Autowired
    IssueReportService issueReportService;

    @PostMapping
    @Operation(
            summary = "Create issue report",
            description = "Creates a transport issue report for a selected category and stop."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Issue report was created successfully."),
            @ApiResponse(responseCode = "400", description = "Request data is invalid.")
    })
    public IssueReport issueReport(
            @RequestBody(
                    description = "Issue report details submitted by a user.",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = IssueReportUserDTO.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "title": "Broken ticket machine",
                                      "description": "The card reader does not accept contactless payment.",
                                      "categoryId": 1,
                                      "stopId": 12,
                                      "routeId": 4
                                    }
                                    """)
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody IssueReportUserDTO reportDto) {
        return issueReportService.create(reportDto);
    }

//    @GetMapping("/reports")
//    public List<IssueReport> getReports() {
//        return issueReportService.findAllReports();
//    }
}
