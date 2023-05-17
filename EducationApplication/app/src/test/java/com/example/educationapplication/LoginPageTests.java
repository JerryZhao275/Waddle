package com.example.educationapplication;

import com.example.educationapplication.viewmodels.LoginViewModel;

import org.junit.Assert;
import org.junit.Test;

public class LoginPageTests {

    private final LoginViewModel model =  new LoginViewModel();

    @Test
    public void test_post_successful_login_message_surfaces() {
        model.setEmail("u7499989@anu.edu.au");
        model.setPassword("password1");
        model.login(()->{});
        Assert.assertNull(model.getErrorMessage());
    }

    @Test
    public void test_invalid_email_message_surfaces() {
        model.setEmail("invalid.email");
        model.setPassword("Password1");
        model.login(()->{});
        Assert.assertEquals("Invalid email. Check your spelling and try again.", model.getErrorMessage());
    }

    @Test
    public void test_incorrect_login_message_surfaces() {
        model.setEmail("notarealuser@anu.edu.au");
        model.setPassword("Password1");
        model.login(()->{});
        Assert.assertEquals("Could not find the user specified. Check your spelling and try again.", model.getErrorMessage());
    }

}
