package com.example.educationapplication;

import com.example.educationapplication.integration.database.MockWaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.util.StringUtils;
import com.example.educationapplication.viewmodels.LoginViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import dataObjects.LoginUserDto;

public class LoginPageTests {

    private LoginViewModel model;

    @Before
    public void setUp() {
        List<LoginUserDto> mockedUsers = List.of(
                new LoginUserDto("12345", "MathewRichards","u7499989@anu.edu.au", "password1"),
                new LoginUserDto("54321", "TestUser","other@testuser.com", "password2"),
                new LoginUserDto("43242", "AdminAdmin", "admin@admin.au", "password3")
        );
        this.model = new LoginViewModel();
    }

    @Test
    public void test_post_successful_login_message_surfaces() {
        model.setEmail("u7499989@anu.edu.au");
        model.setPassword("Password1");
        model.login();
        Assert.assertTrue(StringUtils.isEmpty(model.getErrorMessage()));
    }

    @Test
    public void test_invalid_email_message_surfaces() {
        model.setEmail("invalid.email");
        model.setPassword("Password1");
        model.login();
        Assert.assertEquals("Invalid email. Check your spelling and try again.", model.getErrorMessage());
    }

    @Test
    public void test_incorrect_login_message_surfaces() {
        model.setEmail("notarealuser@anu.edu.au");
        model.setPassword("Password1");
        model.login();
        Assert.assertEquals("Could not find the user specified. Check your spelling and try again.", model.getErrorMessage());
    }

}
