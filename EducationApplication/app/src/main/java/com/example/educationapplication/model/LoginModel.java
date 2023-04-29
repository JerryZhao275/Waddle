package com.example.educationapplication.model;

public final class LoginModel {

    private String email;
    private String password;
    private String errorMessage;

    public LoginModel(String email, String password) {
        this.email = email;
        this.password = password;
        this.errorMessage = null;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
