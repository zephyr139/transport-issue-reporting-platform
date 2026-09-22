package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.model;

import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.enums.Circle;
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Circle circle;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequenceNumber ASC")
    private List<Stop> stops;

}
