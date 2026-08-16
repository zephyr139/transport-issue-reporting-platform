package com.zephyr_jarvis.transport_issue_reporting_platform.services;

import com.zephyr_jarvis.transport_issue_reporting_platform.dtos.RegisterDTO;
import com.zephyr_jarvis.transport_issue_reporting_platform.model.Users;
import com.zephyr_jarvis.transport_issue_reporting_platform.repositories.UsersRepo;
import com.zephyr_jarvis.transport_issue_reporting_platform.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepo repo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder encoder;

    public Users register(RegisterDTO dto) {
        Users user = new Users();

        user.setEmail(dto.email());
        user.setPassword(encoder.encode(dto.password()));
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setAge(dto.age());

        return repo.save(user);
    }

    public String login(String username, String password) {
        try {
            Authentication auth =
                    authManager.authenticate
                            (new UsernamePasswordAuthenticationToken(username, password));

            if (auth.isAuthenticated()) {
                return jwtService.generateToken(username);
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("Failed to log in");
        }

        return "Failed";
    }
}
