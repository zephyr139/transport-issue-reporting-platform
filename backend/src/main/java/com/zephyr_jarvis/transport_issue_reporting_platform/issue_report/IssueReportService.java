package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report;

import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.model.Category;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.model.IssueReport;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.model.Route;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.model.Stop;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.repos.CategoryRepo;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.repos.IssueReportRepo;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.repos.RouteRepo;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.repos.StopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;

@Service
public class IssueReportService {

    @Autowired
    IssueReportRequestValidator validator;

    @Autowired
    IssueReportRepo issueReportRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    StopRepo stopRepo;

    @Autowired
    private RouteRepo routeRepo;

    public IssueReport create(IssueReportUserDTO reportDto) {
        validator.validate(reportDto);

        IssueReport report = new IssueReport();

        report.setTitle(reportDto.title());
        report.setDescription(reportDto.description());

        Category category = categoryRepo.findById(reportDto.categoryId())
                        .orElseThrow(() -> new RuntimeException("No such category is System"));
        report.setCategory(category);

        Stop stop = stopRepo.findById(reportDto.stopId())
                        .orElseThrow(() -> new RuntimeException("No such stop is System"));

        report.setStop(stop);
        report.setLatitude(stop.getLat());
        report.setLongitude(stop.getLon());

        if (reportDto.routeId() != null) {
            Route route = routeRepo.findById(reportDto.routeId())
                    .orElseThrow(() -> new RuntimeException("No such route is System"));
            report.setRoute(route);
        } else {
            report.setRoute(null);
        }

        report.setCreationDate(new Timestamp(System.currentTimeMillis()));

        return issueReportRepo.save(report);
    }

}
