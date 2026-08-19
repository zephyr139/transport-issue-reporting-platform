package com.zephyr_jarvis.transport_issue_reporting_platform.services;

import com.zephyr_jarvis.transport_issue_reporting_platform.dtos.RegisterDTO;
import com.zephyr_jarvis.transport_issue_reporting_platform.repositories.UsersRepo;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidationManager {
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])" +
            "(?=.*[a-z])" +
            "(?=.*[A-Z])" +
            "(?=.*[@#$%^&+=.])" +
            "(?=\\S+$).{4,12}$";
//            TODO: change the shortest possible pattern;

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    @Autowired
    private UsersRepo usersRepo;

    public void validateRegistration(RegisterDTO dto) {
        if (!isFreeEmail(dto.email())) {
            throw new ValidationException("Username with such email already exists");
        }

        if (!isPasswordValid(dto.password())) {
            throw new ValidationException("Password is incorrect. \n" +
                    "Password must contain at least ");
        }

        if (!dto.password().equals(dto.passwordConfirmation())) {
            throw new ValidationException("Passwords do not match");
        }
    }

    private boolean isFreeEmail(String email) {
        return usersRepo.findByEmail(email) == null;
    }

    public boolean isPasswordValid(@NotNull String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
