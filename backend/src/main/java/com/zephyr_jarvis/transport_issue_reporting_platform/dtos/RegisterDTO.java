package com.zephyr_jarvis.transport_issue_reporting_platform.dtos;

import jakarta.validation.constraints.*;

public record RegisterDTO(

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 4, max = 12, message = "Password must be between 4 and 12 characters") // TODO: change the min password length
        String password,

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @Min(value = 16, message = "Age must be at least 16")
        @Max(value = 120, message = "Age must be realistic")
        int age
        ) {
}
