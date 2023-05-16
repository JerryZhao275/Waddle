package com.example.educationapplication.integration.database;

import com.example.educationapplication.search.Exp;

import java.util.List;

import dataObjects.CourseDto;
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
    public void fetchAllCoursesForSearch(Exp expression, CustomOnCompleteListener listener);
    public List<UserDto> getQueryUsers();
    public List<CourseDto> getQueryCourses();
    UserDto getUserDetails();

    void createNewUserDataInstance(UserDto user, String password, CustomOnCompleteListener listener);

    boolean signIn(String username, String password, CustomOnCompleteListener listener);

    void setNullUser();

    public void fetchUserCourses(CustomOnCompleteListener listener);
    public List<CourseDto> getUserCourses();
    public void addCourse(CourseDto course, CustomOnCompleteListener listener);
    public void synchCourses(CustomOnCompleteListener listener);
    public void synchUsers(CustomOnCompleteListener listener);
    void signOut();

    void signInDataInstances(String email, String password);
}
