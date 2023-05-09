package com.example.educationapplication.integration.database;

import dataObjects.CustomOnCompleteListener;
import dataObjects.LoginUserDto;
import dataObjects.UserDto;
import dataObjects.UserDto;

public interface WaddleDatabaseServiceClient {
    LoginUserDto getUser(String email, String password);
    void createNewUser(UserDto user, String password);
    LoginUserDto getCurrentUser(); // TODO dto LoggedInUser { user: User, firebaseUser: FirebaseUser, sessionId, etc.?  }
    void setUserDetails(CustomOnCompleteListener listener);
    UserDto getUserDetails();
    boolean signIn(String username, String password);
    void signOut();
}
