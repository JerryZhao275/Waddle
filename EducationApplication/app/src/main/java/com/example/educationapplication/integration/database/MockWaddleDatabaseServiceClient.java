package com.example.educationapplication.integration.database;

import java.util.List;
import java.util.UUID;

import dataObjects.Student;
import dataObjects.User;

public class MockWaddleDatabaseServiceClient implements WaddleDatabaseServiceClient {

    final private List<User> mockedUsers;

    private User currentUser = null;

    public MockWaddleDatabaseServiceClient(List<User> mockedUsers) {
        this.mockedUsers = mockedUsers;
    }

    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public boolean signIn(String username, String password) {
        User userToLogInAs = getUser(username, password);
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
    public User getUser(String email, String password) {
        return mockedUsers.stream().filter(user -> email.equalsIgnoreCase(user.getEmail()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void createNewUser(String email, String password) {
        this.mockedUsers.add(new Student(UUID.randomUUID().hashCode()).withEmail(email));
    }

}
