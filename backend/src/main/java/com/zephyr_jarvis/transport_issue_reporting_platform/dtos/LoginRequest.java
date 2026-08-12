package com.zephyr_jarvis.transport_issue_reporting_platform.dtos;

public record LoginRequest(
        String username,
        String password
) {
}
