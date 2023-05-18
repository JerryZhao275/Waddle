package com.example.educationapplication.integration.database;

import com.example.educationapplication.search.Exp;
import com.example.educationapplication.search.dataObjects.CommentDto;
import com.example.educationapplication.search.dataObjects.CourseDto;
import com.example.educationapplication.search.dataObjects.CustomOnCompleteListener;
import com.example.educationapplication.search.dataObjects.DiscussionDto;
import com.example.educationapplication.search.dataObjects.LoginUserDto;
import com.example.educationapplication.search.dataObjects.UserDto;

import java.util.List;


public interface WaddleDatabaseServiceClient {
    LoginUserDto getUser(String email, String password);
    void createNewUser(UserDto user, String password);
    LoginUserDto getCurrentUser(); // TODO dto LoggedInUser { user: User, firebaseUser: FirebaseUser, sessionId, etc.?  }
    void fetchUserDetails(CustomOnCompleteListener listener);
    public void fetchAllUsersForSearch(Exp expression, CustomOnCompleteListener listener);
    public void fetchAllCoursesForSearch(Exp expression, CustomOnCompleteListener listener);
    public List<UserDto> getQueryUsers();
    public List<CourseDto> getQueryCourses();

    UserDto getOtherUserDetails();

    UserDto getUserDetails();
    String getCurrentUserId();

    void createNewUserDataInstance(UserDto user, String password, CustomOnCompleteListener listener);

    void fetchOtherUserDetails(UserDto user, CustomOnCompleteListener listener);

    boolean signIn(String username, String password, CustomOnCompleteListener listener);

    void setNullUser();

    public void fetchUserCourses(CustomOnCompleteListener listener);
    public List<CourseDto> getUserCourses();
    public void addCourse(CourseDto course, CustomOnCompleteListener listener);

    void addDiscussion(DiscussionDto discussion, CustomOnCompleteListener listener);

    public void synchCourses(CustomOnCompleteListener listener);
    public void synchUsers(CustomOnCompleteListener listener);

    void addComment(CommentDto comment, CustomOnCompleteListener listener);

    public void addStudentToCourse(String course, CustomOnCompleteListener listener);
    void signOut();

    void signInDataInstances(String email, String password);
    public void syncDiscussions(String courseId,CustomOnCompleteListener listener);
    public List<DiscussionDto> getDiscussions();
    public void syncComments(String discussionID, CustomOnCompleteListener listener);
    public List<CommentDto> getComments();
}
