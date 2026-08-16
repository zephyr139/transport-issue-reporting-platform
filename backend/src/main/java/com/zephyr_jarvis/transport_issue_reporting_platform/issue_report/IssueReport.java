package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "issue_reports")
@Getter
@Setter
public class IssueReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    User provides:

    private String title;

    private String description;

    @ManyToOne
    @JoinTable(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "stop_id")
    private Stop stop;

//    Optional
    private Timestamp approximateOccurrence;

//  Optional
    private VehiecleType vehiecleType;
//    Optional
    private String vehicleNumber;

//    System provides:

    private BigDecimal latitude;

    private BigDecimal longitude;

    private Timestamp creationDate;

//    Department provides:

    private IssueReportStatus status;

//
//    private Employee assignedEmployee;

//    private department

//    private resolution

//    assignedDate

}
