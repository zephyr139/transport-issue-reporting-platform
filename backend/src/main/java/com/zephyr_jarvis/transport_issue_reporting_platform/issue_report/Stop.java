package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report;

import jakarta.persistence.*;

@Entity
@Table(name = "stops")
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
}
