package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

/*
    CRIMINAL_ACCIDENT
    VEHICLE_ACCIDENT
    MISSING_VEHICLE
    DIRTY_STOP_OR_SURROUNDING
    The stop or surrounding area is in poor condition.
*/
}
