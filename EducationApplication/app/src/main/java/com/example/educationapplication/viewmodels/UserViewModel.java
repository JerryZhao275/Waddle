package com.example.educationapplication.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.fragment.app.FragmentTransaction;

import com.example.educationapplication.BR;
import com.example.educationapplication.R;
import com.example.educationapplication.integration.database.FirebaseWaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;
import com.example.educationapplication.views.Fragment.DashboardFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import dataObjects.AdminUserDto;
import dataObjects.CourseDto;
import dataObjects.CustomOnCompleteListener;
import dataObjects.StudentUserDto;
import dataObjects.TeacherUserDto;
import dataObjects.UserDto;

public class UserViewModel extends BaseObservable {
    WaddleDatabaseConfiguration config;
    WaddleDatabaseServiceClient databaseServiceClient;
    UserDto user;
    String userFirstName = "";
    String email = "";
    String userType;
    boolean isTeacher;
    boolean isStudent;
    boolean isStudentReturned = false;

    StudentUserDto student;
    TeacherUserDto teacher;

    List<String> coursesList;

    public UserViewModel() {
        config = ConfigurationManager.configInstance();
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
        databaseServiceClient.fetchUserDetails(new CustomOnCompleteListener(){
            @Override
            public void onComplete() {
                // Get the user details
                user = databaseServiceClient.getUserDetails();

                System.out.println(user.getUserName()+" "+user.getUserEmail());

                // Set the first name and email fields in the view model
                setFirstName(user.getUserFirstName());
                setEmail(user.getUserEmail());
                // Check if the user is a student or a teacher
                isStudent = user instanceof StudentUserDto;
                isTeacher = user instanceof TeacherUserDto;
                System.out.println(user.getUserName());
                if (isStudent) {
                    isStudentReturned = true;
                    // Set the user type to "Student"
                    setUserType("Student");
                    student = (StudentUserDto) user;
                    coursesList = student.getCourses();
                }
                else if (isTeacher) {
                    // Set the user type to "Teacher"
                    setUserType("Teacher");
                    teacher = (TeacherUserDto) user;
                }

            }
        });
    }
    public TeacherUserDto returnUserIfTeacher(){
        return teacher;
    }
    /**
     * Get the email value of the user.
     * @return The current email value of the user.
     */
    @Bindable
    public String getEmail() {
        return email;
    }

    /**
     * Set the email value of the user.
     * @param email The new email value for the user.
     */
    @Bindable
    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    /**
     * Get the first name value of the user.
     * This method is annotated with @Bindable to notify the view when the first name value changes.
     */
    @Bindable
    public String getFirstName() {
        return userFirstName;
    }

    /**
     * Set the first name value of the user.
     * @param firstName The new first name value for the user.
     */
    @Bindable
    public void setFirstName(String firstName) {
        userFirstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    /**
     * Get the user type of the user.
     * @return The current user type value of the user.
     */
    @Bindable
    public String getUserType() {
        return userType;
    }

    /**
     * Set the user type of the user.
     * @param type The new user type value for the user.
     */
    @Bindable
    public void setUserType(String type) {
        this.userType = type;
        notifyPropertyChanged(BR.userType);
    }

    /**
     * Get the list of courses of the user.
     * @return The current list of courses of the user.
     */
    @Bindable
    public List<String> getCourses() {
        // Get the list of courses for the student
        // coursesList = student.getCourses();
        return coursesList;
    }

    /**
     * Add a course into a user's list of courses.
     * @param course The course to be added.
     */
    @Bindable
    public void setCourses(String course) {
        if (coursesList == null) {
            coursesList = new ArrayList<String>();
        }
        coursesList.add(course);
        //student.addCourses(coursesList);
        notifyPropertyChanged(BR.courses);
    }

    @Bindable
    public boolean isStudent() {
        return isStudentReturned;
    }



}
