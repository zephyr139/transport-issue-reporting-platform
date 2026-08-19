package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "stops")
@Getter
@Setter
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private BigDecimal lat;

    private BigDecimal lon;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
}
