package com.zephyr_jarvis.transport_issue_reporting_platform.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record RegisterRequest(

        @Schema(description = "Unique email address used for registration.", example = "alex@example.com")
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @Schema(
                description = "Password must be 4-12 characters and contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no spaces.",
                example = "Pass1!"
        )
        @NotBlank(message = "Password is required")
        @Size(min = 4, max = 12, message = "Password must be between 4 and 12 characters") // TODO: change the min password length
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.])(?=\\S+$).{4,12}$",
                message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no spaces"
        )
        String password,

        @Schema(description = "Must match the password field.", example = "Pass1!")
        @NotBlank(message = "Password is required")
        @Size(min = 4, max = 12, message = "Password must be between 4 and 12 characters") // TODO: change the min password length
        String passwordConfirmation,

        @Schema(description = "User's first name.", example = "Alex")
        @NotBlank(message = "First name is required")
        String firstName,

        @Schema(description = "User's last name.", example = "Smith")
        @NotBlank(message = "Last name is required")
        String lastName,

        @Schema(description = "User age. Must be between 16 and 120.", example = "24")
        @Min(value = 16, message = "Age must be at least 16")
        @Max(value = 120, message = "Age must be realistic")
        int age
        ) {
}
