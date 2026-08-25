package com.zephyr_jarvis.transport_issue_reporting_platform.controllers;


import com.zephyr_jarvis.transport_issue_reporting_platform.dtos.LoginRequest;
import com.zephyr_jarvis.transport_issue_reporting_platform.dtos.RegisterRequest;
import com.zephyr_jarvis.transport_issue_reporting_platform.model.Users;
import com.zephyr_jarvis.transport_issue_reporting_platform.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for user registration and login.")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a user account from the submitted registration data.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User was registered successfully."),
            @ApiResponse(responseCode = "400", description = "Registration data is invalid, email is already used, or passwords do not match.")
    })
    public Users register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Registration details for the new user.",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = RegisterRequest.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "email": "alex@example.com",
                                      "password": "Pass1!",
                                      "passwordConfirmation": "Pass1!",
                                      "firstName": "Alex",
                                      "lastName": "Smith",
                                      "age": 24
                                    }
                                    """)
                    )
            )
            @Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    @Operation(summary = "Log in", description = "Authenticates a user and returns a JWT token.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login was successful."),
            @ApiResponse(responseCode = "401", description = "Username or password is incorrect.")
    })
    public String login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Login credentials.",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = LoginRequest.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "username": "alex@example.com",
                                      "password": "Pass1!"
                                    }
                                    """)
                    )
            )
            @RequestBody LoginRequest request) {
        return userService.login(request.username(), request.password());
    }
}
