package com.example.educationapplication.integration.database;

import com.example.educationapplication.search.Exp;

import java.util.List;

import dataObjects.CustomOnCompleteListener;
import dataObjects.LoginUserDto;
import dataObjects.UserDto;
import dataObjects.UserDto;

public interface WaddleDatabaseServiceClient {
    LoginUserDto getUser(String email, String password);
    void createNewUser(UserDto user, String password);
    LoginUserDto getCurrentUser(); // TODO dto LoggedInUser { user: User, firebaseUser: FirebaseUser, sessionId, etc.?  }
    void fetchUserDetails(CustomOnCompleteListener listener);
    public void fetchAllUsersForSearch(Exp expression, CustomOnCompleteListener listener);
    public List<UserDto> getQueryUsers();
    UserDto getUserDetails();
    boolean signIn(String username, String password);
    void signOut();
}
