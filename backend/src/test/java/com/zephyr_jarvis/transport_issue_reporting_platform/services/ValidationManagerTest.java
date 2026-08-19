package com.zephyr_jarvis.transport_issue_reporting_platform.services;

import com.zephyr_jarvis.transport_issue_reporting_platform.controllers.UserController;
import com.zephyr_jarvis.transport_issue_reporting_platform.dtos.RegisterDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ValidationManagerTest {

    ValidationManager validator = new ValidationManager();

    UserController controller = new UserController();

    @Test
    public void noDigitPassword() {
        Assertions.assertFalse(validator.isPasswordValid("Sr......."));
    }

    @Test
    public void NoLowerCaseCharPassword() {
        Assertions.assertFalse(validator.isPasswordValid("S12......."));
    }

    @Test
    public void NoUpperCaseCharPassword() {
        Assertions.assertFalse(validator.isPasswordValid("se1......"));
    }

    @Test
    public void NoSpecialCharPassword() {
        Assertions.assertFalse(validator.isPasswordValid("Se124354"));
    }

    @Test
    public void tooShortPassword() {
        Assertions.assertFalse(validator.isPasswordValid("sR1"));
    }

    @Test
    public void tooLongPassword() {
        Assertions.assertFalse(validator.isPasswordValid("sR1123......."));
    }

//    TODO: write tests for wrong email
//    @Test
//    public void badEmailDto() {
//        RegisterDTO dto = new RegisterDTO("rybalko@gmail.com", "Sr12435..", "Sr12435..", "Alex", "Rybalko", 16);
//
//      Assertions.assertThrows(, controller.register(dto));
//
//    }

}