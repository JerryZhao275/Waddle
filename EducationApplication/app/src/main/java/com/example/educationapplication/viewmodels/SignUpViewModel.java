package com.example.educationapplication.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.educationapplication.BR;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;
import com.example.educationapplication.util.CommonRegexUtil;
import com.example.educationapplication.util.StringUtils;

import java.util.regex.Pattern;

import dataObjects.AdminUserDto;
import dataObjects.CustomOnCompleteListener;
import dataObjects.StudentUserDto;
import dataObjects.TeacherUserDto;
import dataObjects.UserDto;

public class SignUpViewModel extends BaseObservable {
    private final WaddleDatabaseConfiguration config;
    private final WaddleDatabaseServiceClient databaseServiceClient;
    private String confPassword = "";
    private UserDto userDetails = new StudentUserDto("","","","","","", 0);

    private boolean authorised = false;

    private Boolean isTeacherOrStudent = null;
    private String errorMessage = "";

    private final static String LOGIN_FAILED = "Invalid email. Check your spelling and try again.";
    private final static String EMPTY_FIELD = "Either all fields have not been filled or the passwords don't match.";
    private String password = "";

    public SignUpViewModel() {
        // Get the database configuration
        config = ConfigurationManager.configInstance();
        // Create the database service client
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
    }

    /**
     * Get the database service client.
     *
     * @return The database service client.
     */
    public WaddleDatabaseServiceClient getDatabaseServiceClient() {
        return databaseServiceClient;
    }

    @Bindable
    public String getUserName() {
        return userDetails.getUserName();
    }

    /**
     * Set the username.
     *
     * @param userName The username to set.
     */
    @Bindable
    public void setUserName(String userName) {
        userDetails.setUserName(userName);
        notifyPropertyChanged(BR.userName);
    }

    /**
     * Get the user's first name.
     *
     * @return The user's first name.
     */
    @Bindable
    public String getUserFirstName() {
        return userDetails.getUserFirstName();
    }

    /**
     * Set the user's first name.
     *
     * @param userFirstName The user's first name to set.
     */
    @Bindable
    public void setUserFirstName(String userFirstName) {
        userDetails.setUserFirstName(userFirstName);
        notifyPropertyChanged(BR.userFirstName);
    }

    /**
     * Get the user's last name.
     *
     * @return The user's last name.
     */
    @Bindable
    public String getUserLastName() {
        return userDetails.getUserLastName();
    }

    /**
     * Set the user's last name.
     *
     * @param userLastName The user's last name to set.
     */
    @Bindable
    public void setUserLastName(String userLastName) {
        userDetails.setUserLastName(userLastName);
        notifyPropertyChanged(BR.userLastName);
    }

    /**
     * Get the user's email.
     *
     * @return The user's email.
     */
    @Bindable
    public String getEmail() {
        return userDetails.getUserEmail();
    }

    /**
     * Set the user's email.
     *
     * @param email The user's email to set.
     */
    @Bindable
    public void setEmail(String email) {
        userDetails.setUserEmail(email);
        notifyPropertyChanged(BR.email);
    }

    /**
     * Get the password.
     *
     * @return The password.
     */
    @Bindable
    public String getPassword() {
        return password;
    }

    /**
     * Set the password.
     *
     * @param password The password to set.
     */
    @Bindable
    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    /**
     * Get the confirmation password.
     *
     * @return The confirmation password.
     */
    @Bindable
    public String getConfirmPassword() {
        return confPassword;
    }

    /**
     * Set the confirmation password.
     *
     * @param password The confirmation password to set.
     */
    @Bindable
    public void setConfirmPassword(String password) {
        confPassword = password;
        notifyPropertyChanged(BR.confirmPassword);
    }

    /**
     * Get the error message.
     *
     * @return The error message.
     */
    @Bindable
    public String getErrorMessage(){
        return this.errorMessage;
    }

    /**
     * Set the error message.
     *
     * @param error The error message to set.
     */
    @Bindable
    public void setErrorMessage(String error){
        this.errorMessage = error;
        notifyPropertyChanged(BR.errorMessage);
    }

    /**
     * Get whether the user is a teacher or student.
     *
     * @return True if the user is a teacher, false if the user is a student.
     */
    @Bindable
    public Boolean getIsTeacherOrStudent(){
        return isTeacherOrStudent;
    }

    /**
     * Set whether the user is a teacher or student.
     *
     * @param isTeacher Whether the user is a teacher or not.
     */
    @Bindable
    public void setIsTeacherOrStudent(Boolean isTeacher){
        this.isTeacherOrStudent = isTeacher;
        if(isTeacher){
            userDetails = new TeacherUserDto(userDetails);
        }
        else{
            userDetails = new StudentUserDto(userDetails);
        }
        notifyPropertyChanged(BR.isTeacherOrStudent);

    }

    /**
     * Create a new user.
     */
    public void createUser() {
        // Check if all required fields are filled and passwords match
    public void createUser(CustomOnCompleteListener listener) {
        boolean questionsAnswered = StringUtils.isNotEmpty(userDetails.getUserEmail()) && StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(userDetails.getUserFirstName())
                && StringUtils.isNotEmpty(userDetails.getUserLastName()) && StringUtils.isNotEmpty(userDetails.getUserName()) && StringUtils.isNotEmpty(confPassword) && confPassword.equals(password);
        // Check if the email is valid
        boolean isEmailValid = Pattern.matches(CommonRegexUtil.EMAIL, userDetails.getUserEmail());

        if (!questionsAnswered) {
            setErrorMessage(EMPTY_FIELD);
            return;
        }
        if (!isEmailValid) {
            setErrorMessage(LOGIN_FAILED);
            return;
        }
        setErrorMessage("");
        // Create the new user using the database service client
        getDatabaseServiceClient().createNewUser(userDetails, password);
        getDatabaseServiceClient().createNewUser(userDetails, password, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                listener.onComplete();
            }
        });
    }
}
