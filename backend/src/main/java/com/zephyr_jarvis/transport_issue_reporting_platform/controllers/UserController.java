package com.zephyr_jarvis.transport_issue_reporting_platform.controllers;


import com.zephyr_jarvis.transport_issue_reporting_platform.dtos.LoginRequest;
import com.zephyr_jarvis.transport_issue_reporting_platform.dtos.RegisterDTO;
import com.zephyr_jarvis.transport_issue_reporting_platform.model.Users;
import com.zephyr_jarvis.transport_issue_reporting_platform.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Users register(@Valid @RequestBody RegisterDTO dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request.username(), request.password());
    }
}
