package com.example.educationapplication;

import com.example.educationapplication.viewmodels.LoginViewModel;

import org.junit.Assert;
import org.junit.Test;

public class LoginPageTests {

    private final LoginViewModel model =  new LoginViewModel();

    @Test
    public void test_getters_are_functional() {
        model.setEmail("1");
        model.setPassword("2");
        model.setErrorMessage("3");
        model.setAuthorised(false);

        Assert.assertEquals("1", model.getEmail());
        Assert.assertEquals("2", model.getPassword());
        Assert.assertEquals("3", model.getErrorMessage());
        Assert.assertFalse(model.isAuthorised());
    }

    @Test
    public void test_post_successful_login_message_surfaces() {
        model.setEmail("u7499989@anu.edu.au");
        model.setPassword("password1");
        model.login(()->{});
        Assert.assertNull(model.getErrorMessage());
        Assert.assertTrue(model.isAuthorised());
    }

    @Test
    public void test_invalid_email_message_surfaces() {
        model.setEmail("invalid.email");
        model.setPassword("Password1");
        model.login(()->{});
        Assert.assertEquals("Invalid email. Check your spelling and try again.", model.getErrorMessage());
        Assert.assertFalse(model.isAuthorised());
    }

    @Test
    public void test_incorrect_login_message_surfaces() {
        model.setEmail("notarealuser@anu.edu.au");
        model.setPassword("Password1");
        model.login(()->{});
        Assert.assertEquals("Could not find the user specified. Check your spelling and try again.", model.getErrorMessage());
        Assert.assertFalse(model.isAuthorised());
    }

    @Test
    public void test_empty_fields_error_surfaces() {
        model.setPassword("Password1");
        model.login(()->{});
        Assert.assertEquals("Please fill out all fields.", model.getErrorMessage());
        Assert.assertFalse(model.isAuthorised());
    }

}
