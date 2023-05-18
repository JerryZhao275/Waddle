package com.example.educationapplication.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.example.educationapplication.BR;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;
import java.util.ArrayList;
import java.util.List;
import com.example.educationapplication.search.dataObjects.*;

public class UserViewModel extends BaseObservable {
    WaddleDatabaseConfiguration config;
    WaddleDatabaseServiceClient databaseServiceClient;
    UserDto user;
    String userFirstName = "";
    String userLastName = "";
    String email = "";
    String userType;
    boolean isTeacher;
    boolean isStudent;
    boolean isStudentReturned = false;

    StudentUserDto student;
    TeacherUserDto teacher;

    static List<String> coursesList = new ArrayList<>();
    List<CourseDto> userCourses = new ArrayList<>();

    public static List<String> getCoursesList(){
        return coursesList;
    }

    public UserViewModel() {
        // Initialize database configuration and service client
        config = ConfigurationManager.configInstance();
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
        // Synchronize users from the database
        databaseServiceClient.synchUsers(new CustomOnCompleteListener(){
            @Override
            public void onComplete() {
                // Get the user details
                user = databaseServiceClient.getUserDetails();

                // Set the first name, last name, and email fields in the view model
                setFirstName(user.getUserFirstName());
                setLastName(user.getUserLastName());
                setEmail(user.getUserEmail());
                // Check if the user is a student or a teacher
                isStudent = user instanceof StudentUserDto;
                isTeacher = user instanceof TeacherUserDto;
                if (isStudent) {
                    isStudentReturned = true;
                    // Set the user type to "Student"
                    setUserType("Student");
                    student = (StudentUserDto) user;
                    //coursesList = student.getCourses();
                }
                else if (isTeacher) {
                    // Set the user type to "Teacher"
                    setUserType("Teacher");
                    teacher = (TeacherUserDto) user;
                }
            }
        });
    }

    /**
     * Fetches the course details of the user asynchronously.
     *
     * @param listener The listener to be called when the fetching is complete.
     */
    public void fetchUserCourseDetails(CustomOnCompleteListener listener){
        databaseServiceClient.synchCourses(new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                setCourses(null);
                List<CourseDto> courses = databaseServiceClient.getUserCourses();
                userCourses = courses;
                if(courses!=null) {
                    for (CourseDto course : courses) {
                        setCourses(course.getCourseName());
                    }
                }
                listener.onComplete();
            }
        });
    }

    /**
     * Fetches details of another user asynchronously.
     *
     * @param user     The user to fetch details for.
     * @param listener The listener to be called when the fetching is complete.
     */
    public void fetchOtherUserDetails(UserDto user, CustomOnCompleteListener listener){
        databaseServiceClient.fetchOtherUserDetails(user, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {

                listener.onComplete();
            }
        });
    }

    /**
     * Joins a course for the user.
     *
     * @param course   The course to join.
     * @param listener The listener to be called when the joining is complete.
     */
    public void joinCourse(String course, CustomOnCompleteListener listener){
        databaseServiceClient.addStudentToCourse(course, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                listener.onComplete();
            }
        });
    }

    /**
     * Gets the details of another user.
     *
     * @return The details of the other user.
     */
    public UserDto getOtherUserDetails(){
        return databaseServiceClient.getOtherUserDetails();
    }

    /**
     * Gets the course details of the user.
     *
     * @return The course details of the user.
     */
    public List<CourseDto> getUserCourseDetails(){
        return userCourses;
    }

    /**
     * Returns the user as a TeacherUserDto if the user is a teacher.
     *
     * @return The user as a TeacherUserDto if the user is a teacher, null otherwise.
     */
    public TeacherUserDto returnUserIfTeacher(){
        return teacher;
    }

    /**
     * Get the email value of the user.
     *
     * @return The current email value of the user.
     */
    @Bindable
    public String getEmail() {
        return email;
    }

    /**
     * Set the email value of the user.
     *
     * @param email The new email value for the user.
     */
    @Bindable
    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    /**
     * Get the first name value of the user.
     *
     * @return The current first name value of the user.
     */
    @Bindable
    public String getFirstName() {
        return userFirstName;
    }

    /**
     * Set the first name value of the user.
     *
     * @param firstName The new first name value for the user.
     */
    @Bindable
    public void setFirstName(String firstName) {
        userFirstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    /**
     * Get the last name value of the user.
     *
     * @return The current last name value of the user.
     */
    @Bindable
    public String getLastName() {
        return userLastName;
    }

    /**
     * Set the last name value of the user.
     *
     * @param lastName The new last name value for the user.
     */
    @Bindable
    public void setLastName(String lastName) {
        userLastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    /**
     * Get the user type of the user.
     *
     * @return The current user type value of the user.
     */
    @Bindable
    public String getUserType() {
        return userType;
    }

    /**
     * Set the user type of the user.
     *
     * @param type The new user type value for the user.
     */
    @Bindable
    public void setUserType(String type) {
        this.userType = type;
        notifyPropertyChanged(BR.userType);
    }

    /**
     * Get the list of courses of the user.
     *
     * @return The current list of courses of the user.
     */
    @Bindable
    public List<String> getCourses() {
        // Get the list of courses for the student
        // coursesList = student.getCourses();
        return coursesList;
    }

    /**
     * Add a course to the user's list of courses.
     *
     * @param course The course to be added.
     */
    @Bindable
    public void setCourses(String course) {
        if (coursesList == null || course == null) {
            coursesList = new ArrayList<String>();
        }
        coursesList.add(course);
        //student.addCourses(coursesList);
        notifyPropertyChanged(BR.courses);
    }

    /**
     * Checks if the user is a student.
     *
     * @return true if the user is a student, false otherwise.
     */
    @Bindable
    public boolean isStudent() {
        return isStudentReturned;
    }

    /**
     * Converts the list of courses to a string representation.
     *
     * @return The string representation of the list of courses.
     */
    public static String convertListToString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(coursesList == null){
            coursesList = new ArrayList<>();
        }
        int i = 0;
        for (String item : coursesList) {
            if (i != 0) stringBuilder.append(item).append("\n");
            i++;
        }
        return stringBuilder.toString();
    }
}