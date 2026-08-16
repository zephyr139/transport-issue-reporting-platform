package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "routes")
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int number;

    @OneToMany(mappedBy = "route")
    private List<Stop> stops;

    private String name;

    private String startStation;

    private String endStation;
}
