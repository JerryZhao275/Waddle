package com.example.educationapplication.integration.database;

import dataObjects.User;

public interface WaddleDatabaseServiceClient {
    User getUser(String email, String password);
    void createNewUser(String email, String password);
    User getCurrentUser(); // TODO dto LoggedInUser { user: User, firebaseUser: FirebaseUser, sessionId, etc.?  }
    boolean signIn(String username, String password);
    void signOut();
}
