package com.example.educationapplication.integration.database;

import java.util.List;
import java.util.UUID;

import dataObjects.CustomOnCompleteListener;
import dataObjects.LoginUserDto;
import dataObjects.UserDto;

public class MockWaddleDatabaseServiceClient implements WaddleDatabaseServiceClient {
    final private List<LoginUserDto> mockedUsers;

    private LoginUserDto currentUser = null;

    public MockWaddleDatabaseServiceClient(List<LoginUserDto> mockedUsers) {
        this.mockedUsers = mockedUsers;
    }

    @Override
    public LoginUserDto getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public void setUserDetails(CustomOnCompleteListener listener) {
        return;
    }

    @Override
    public UserDto getUserDetails() {
        return null;
    }

    @Override
    public boolean signIn(String username, String password) {
        LoginUserDto userToLogInAs = getUser(username, password);
        if (userToLogInAs != null) {
            this.currentUser = userToLogInAs;
            return true;
        }
        return false;
    }

    @Override
    public void signOut() {
        this.currentUser = null;
    }

    @Override
    public LoginUserDto getUser(String email, String password) {
        return mockedUsers.stream().filter(user -> email.equalsIgnoreCase(user.getLoginUserEmail())&&password.equals(user.getPassword()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void createNewUser(UserDto user, String password) {
        this.mockedUsers.add(new LoginUserDto(UUID.randomUUID().toString(), user.getUserEmail(), user.getUserEmail(), password));
    }

}
