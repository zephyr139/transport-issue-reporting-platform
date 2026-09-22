package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report;

import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.data_specification.IssueReportSpecification;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.enums.IssueReportStatus;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.model.Category;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.model.IssueReport;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.model.Stop;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos.IssueReportFilterDto;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos.response.CategoryResponseDTO;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos.response.IssueReportResponseDto;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos.request.UserIssueReportRequestDto;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos.response.RouteResponseDTO;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.report_dtos.response.StopResponseDTO;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.repos.CategoryRepo;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.repos.IssueReportRepo;
import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.repos.StopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;

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


    public IssueReportResponseDto create(UserIssueReportRequestDto reportDto) {
        validator.validate(reportDto);

        IssueReport report = new IssueReport();

        report.setTitle(reportDto.title());
        report.setDescription(reportDto.description());
        report.setStatus(IssueReportStatus.SUBMITTED);

        Category category = categoryRepo.findById(reportDto.categoryId())
                        .orElseThrow(() -> new RuntimeException("No such category is System"));
        report.setCategory(category);

        Stop stop = determineStop(reportDto.stationId(), reportDto.nextStationId());

        report.setRoute(stop.getRoute());
        report.setStop(stop);
        report.setLatitude(stop.getStation().getLatitude());
        report.setLongitude(stop.getStation().getLongitude());
        report.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        issueReportRepo.save(report);

        return toResponseDto(report);
    }

    public List<IssueReportResponseDto> searchReports(IssueReportFilterDto filter, Pageable pageable) {
        // Specification contains the dynamic WHERE conditions. Pageable contains
        // LIMIT/OFFSET and ORDER BY information for pagination and sorting.
        Specification<IssueReport> specification = IssueReportSpecification.filter(filter);

        // JpaSpecificationExecutor provides findAll(specification, pageable).
        // The database returns a Page<IssueReport>; map converts each entity to
        // an API response DTO before getContent returns only the current page list.
        return issueReportRepo.findAll(specification,pageable).map(this::toResponseDto).getContent();
    }


    private IssueReportResponseDto toResponseDto(IssueReport report) {
        CategoryResponseDTO category =  new CategoryResponseDTO(
                report.getCategory().getId(),report.getCategory().getName());
        RouteResponseDTO route = new RouteResponseDTO(
                report.getRoute().getId(),report.getRoute().getNumber(),report.getRoute().getCircle());

        StopResponseDTO stop = new StopResponseDTO(
                report.getStop().getId(),report.getStop().getName(),report.getStop().getSequenceNumber());


        return new IssueReportResponseDto
                (report.getId(), report.getTitle(), report.getDescription(),category,route,stop,
                        report.getLatitude(),report.getLongitude(),report.getStatus(),report.getCreatedAt());
    }

    private Stop determineStop(long currentStationId, long nextStationId) {
        // One station can appear on many routes, so this gives us every Stop
        // that uses the current physical station.
        List<Stop> currentStops = stopRepo.findByStationId(currentStationId)
                .orElseThrow(() -> new RuntimeException("No such stop is System"));

        // Same idea for the next station: it may also belong to many routes.
        List<Stop> nextStops = stopRepo.findByStationId(nextStationId)
                .orElseThrow(() -> new RuntimeException("No such stop" +
                        "is System"));

        // We compare every possible current stop with every possible next stop.
        // The matching pair tells us which route the user is travelling on.
        for (Stop currentStop : currentStops) {
            for (Stop nextStop : nextStops) {
                // Both stops must belong to the same route.
                // Example: current station is stop 5 on route A,
                // next station must also be on route A.
                boolean sameRoute = currentStop.getRoute().getId() == nextStop.getRoute().getId();

                // The next stop must come immediately after the current stop.
                // Example: if current sequenceNumber is 5, next must be 6.
                boolean nextStopComesAfterCurrentStop =
                        nextStop.getSequenceNumber() == currentStop.getSequenceNumber() + 1;

                if (sameRoute && nextStopComesAfterCurrentStop) {
                    // Returning currentStop is enough because it already contains:
                    // - the current station
                    // - the route
                    // - the sequence number on that route
                    return currentStop;
                }

            }
        }

        throw new RuntimeException("Stations are not consecutive on any route");
    }

}
