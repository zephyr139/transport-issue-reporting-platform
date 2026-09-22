package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report;


import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos.request.UserIssueReportRequestDto;
import org.springframework.stereotype.Component;

@Component
//    TODO: implement logic
public class IssueReportRequestValidator {

    public void validate(UserIssueReportRequestDto reportDto) {

    }

    public boolean isSuchStation(long stationId) {
        return true;
    }

    public boolean isSuchCategory(long categoryId) {
        return true;
    }

    public boolean isDifferentStations(long stationId, long nextStationId) {
        return stationId != nextStationId;
    }

}
