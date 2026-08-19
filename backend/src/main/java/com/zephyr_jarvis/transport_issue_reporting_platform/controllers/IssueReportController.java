package com.zephyr_jarvis.transport_issue_reporting_platform.controllers;

import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.IssueReport;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.IssueReportService;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.IssueReportUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IssueReportController {

    @Autowired
    IssueReportService issueReportService;

    @PostMapping
    public IssueReport issueReport(@RequestBody IssueReportUserDTO reportDto) {
        return issueReportService.create(reportDto);
    }

//    @GetMapping("/reports")
//    public List<IssueReport> getReports() {
//        return issueReportService.findAllReports();
//    }
}