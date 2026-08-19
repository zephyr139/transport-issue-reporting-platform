package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report;


import org.springframework.stereotype.Component;

@Component
//    TODO: implement logic
public class IssueReportRequestValidator {

    public void validate(IssueReportUserDTO reportDto) {

    }

    public boolean isSuchStop(long stopId) {
        return true;
    }

    public boolean isSuchRoute(long routeId) {
        return true;
    }

    public boolean isSuchCategory(long categoryId) {
        return true;
    }
}
