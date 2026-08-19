package com.zephyr_jarvis.transport_issue_reporting_platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private int age;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
