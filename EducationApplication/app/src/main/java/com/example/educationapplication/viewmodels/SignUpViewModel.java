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
import dataObjects.StudentUserDto;
import dataObjects.TeacherUserDto;
import dataObjects.UserDto;

public class SignUpViewModel extends BaseObservable {
    private final boolean useMock;
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

    public SignUpViewModel(boolean useMock) {
        this.useMock = useMock;
        config = ConfigurationManager.configInstance(useMock);
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
    }

    public WaddleDatabaseServiceClient getDatabaseServiceClient() {
        return databaseServiceClient;
    }

    @Bindable
    public String getUserName() {
        return userDetails.getUserName();
    }

    @Bindable
    public void setUserName(String userName) {
        userDetails.setUserName(userName);
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getUserFirstName() {
        return userDetails.getUserFirstName();
    }

    @Bindable
    public void setUserFirstName(String userName) {
        userDetails.setUserFirstName(userName);
        notifyPropertyChanged(BR.userFirstName);
    }

    @Bindable
    public String getUserLastName() {
        return userDetails.getUserLastName();
    }

    @Bindable
    public void setUserLastName(String userName) {
        userDetails.setUserLastName(userName);
        notifyPropertyChanged(BR.userLastName);
    }

    @Bindable
    public String getEmail() {
        return userDetails.getUserEmail();
    }

    @Bindable
    public void setEmail(String email) {
        userDetails.setUserEmail(email);
        notifyPropertyChanged(BR.email);
    }



    @Bindable
    public String getPassword() {
        return password;
    }

    @Bindable
    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getConfirmPassword() {
        return confPassword;
    }

    @Bindable
    public void setConfirmPassword(String password) {
        confPassword = password;
        notifyPropertyChanged(BR.confirmPassword);
    }
    @Bindable
    public String getErrorMessage(){
        return this.errorMessage;
    }
    @Bindable
    public void setErrorMessage(String error){
        this.errorMessage = error;
        notifyPropertyChanged(BR.errorMessage);
    }
    @Bindable
    public Boolean getIsTeacherOrStudent(){
        return isTeacherOrStudent;
    }
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
    public void createUser() {
        boolean questionsAnswered = StringUtils.isNotEmpty(userDetails.getUserEmail()) && StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(userDetails.getUserFirstName())
                && StringUtils.isNotEmpty(userDetails.getUserLastName()) && StringUtils.isNotEmpty(userDetails.getUserName()) && StringUtils.isNotEmpty(confPassword) && confPassword.equals(password);
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
        getDatabaseServiceClient().createNewUser(userDetails, password);
    }
}
