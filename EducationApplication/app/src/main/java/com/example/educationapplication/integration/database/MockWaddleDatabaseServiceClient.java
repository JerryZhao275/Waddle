package com.example.educationapplication.integration.database;

import com.example.educationapplication.search.Exp;
import com.example.educationapplication.search.dataObjects.LoginUserDto;

import java.util.List;
import java.util.UUID;

import com.example.educationapplication.search.dataObjects.CommentDto;
import com.example.educationapplication.search.dataObjects.CourseDto;
import com.example.educationapplication.search.dataObjects.CustomOnCompleteListener;
import com.example.educationapplication.search.dataObjects.DiscussionDto;
import com.example.educationapplication.search.dataObjects.UserDto;

public class MockWaddleDatabaseServiceClient implements WaddleDatabaseServiceClient {
    final private List<LoginUserDto> mockedUsers;

    private LoginUserDto currentUser = null;

    /**
     * Another implementation of a database service client used for mocking real behaviour
     * @param mockedUsers
     */
    public MockWaddleDatabaseServiceClient(List<LoginUserDto> mockedUsers) {
        this.mockedUsers = mockedUsers;
    }

    @Override
    public LoginUserDto getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public void fetchUserDetails(CustomOnCompleteListener listener) {
        return;
    }

    @Override
    public void fetchAllUsersForSearch(Exp expression, CustomOnCompleteListener listener) {
        return;
    }

    @Override
    public void fetchAllCoursesForSearch(Exp expression, CustomOnCompleteListener listener) {
    }

    @Override
    public List<UserDto> getQueryUsers() {
        return null;
    }

    @Override
    public List<CourseDto> getQueryCourses() {
        return null;
    }

    @Override
    public UserDto getOtherUserDetails() {
        return null;
    }

    @Override
    public UserDto getUserDetails() {
        return null;
    }

    @Override
    public String getCurrentUserId() {
        return null;
    }

    @Override
    public void createNewUserDataInstance(UserDto user, String password, CustomOnCompleteListener listener) {

    }

    @Override
    public void fetchOtherUserDetails(UserDto user, CustomOnCompleteListener listener) {

    }

    @Override
    public boolean signIn(String username, String password, CustomOnCompleteListener listener) {
        LoginUserDto userToLogInAs = getUser(username, password);
        if (userToLogInAs != null) {
            this.currentUser = userToLogInAs;
            listener.onComplete();
            return true;
        }
        listener.onComplete();
        return false;
    }

    @Override
    public void setNullUser() {

    }

    @Override
    public void fetchUserCourses(CustomOnCompleteListener listener) {

    }

    @Override
    public List<CourseDto> getUserCourses() {
        return null;
    }

    @Override
    public void addCourse(CourseDto course, CustomOnCompleteListener listener) {

    }

    @Override
    public void addDiscussion(DiscussionDto discussion, CustomOnCompleteListener listener) {

    }

    @Override
    public void synchCourses(CustomOnCompleteListener listener) {

    }

    @Override
    public void synchUsers(CustomOnCompleteListener listener) {

    }

    @Override
    public void addComment(CommentDto comment, CustomOnCompleteListener listener) {

    }

    @Override
    public void addStudentToCourse(String course, CustomOnCompleteListener listener) {

    }

    @Override
    public void signOut() {
        this.currentUser = null;
    }

    @Override
    public void signInDataInstances(String email, String password) {
    }

    @Override
    public void syncDiscussions(String courseId, CustomOnCompleteListener listener) {

    }

    @Override
    public List<DiscussionDto> getDiscussions() {
        return null;
    }

    @Override
    public void syncComments(String discussionID,CustomOnCompleteListener listener) {

    }

    @Override
    public List<CommentDto> getComments() {
        return null;
    }

    @Override
    public LoginUserDto getUser(String email, String password) {
        return mockedUsers.stream().filter(user -> email.equalsIgnoreCase(user.getLoginUserEmail())&&password.equals(user.getPassword()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void createNewUser(UserDto user, String password, CustomOnCompleteListener listener) {
        this.mockedUsers.add(new LoginUserDto(UUID.randomUUID().toString(), user.getUserEmail(), user.getUserEmail(), password));
    }

}
