package com.example.educationapplication.integration.database;

import android.content.Context;

import java.util.List;
import java.util.UUID;

import dataObjects.LoginUserDto;
import dataObjects.StudentLoginDto;
import dataObjects.StudentUserDto;
import dataObjects.UserDto;

public class MockWaddleDatabaseServiceClient implements WaddleDatabaseServiceClient {
    final private Context context;
    final private List<LoginUserDto> mockedUsers;

    private LoginUserDto currentUser = null;

    public MockWaddleDatabaseServiceClient(List<LoginUserDto> mockedUsers, Context context) {
        this.context =  context;
        this.mockedUsers = mockedUsers;
    }

    @Override
    public LoginUserDto getCurrentUser() {
        return this.currentUser;
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
    public void createNewUser(UserDto user) {
        this.mockedUsers.add(new StudentLoginDto(UUID.randomUUID().toString(), user.getUserEmail()+user.getUserPassword(), user.getUserEmail(), user.getUserPassword()));
    }

}
