package com.example.educationapplication.viewmodels;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.educationapplication.BR;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;
import com.example.educationapplication.model.LoginModel;
import com.example.educationapplication.util.CommonRegexUtil;
import com.example.educationapplication.util.StringUtils;

import java.util.regex.Pattern;

import dataObjects.AdminLoginDto;
import dataObjects.AdminUserDto;
import dataObjects.LoginUserDto;
import dataObjects.UserDto;

public class SignUpViewModel extends BaseObservable {
    private final Context context;
    private final boolean useMock;
    private final WaddleDatabaseConfiguration config;
    private final WaddleDatabaseServiceClient databaseServiceClient;
    private String confPassword = "";
    private final UserDto userDetails = new AdminUserDto("","","","","","","");;

    private boolean authorised = false;

    private final static String LOGIN_FAILED = "Invalid email. Check your spelling and try again.";
    private final static String EMPTY_FIELD = "Either fields have not been filled or the passwords don't match.";

    public SignUpViewModel(boolean useMock, Context context) {
        this.context = context;
        this.useMock = useMock;
        config = ConfigurationManager.configInstance(useMock);
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config, this.context);
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
        System.out.println(userDetails.getUserName());
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
        System.out.println(userDetails.getUserEmail());
        userDetails.setUserEmail(email);
        notifyPropertyChanged(BR.email);
    }



    @Bindable
    public String getPassword() {
        return userDetails.getUserPassword();
    }

    @Bindable
    public void setPassword(String password) {
        userDetails.setUserPassword(password);
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

    public void setErrorMessage(String error){
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }

    public void createUser() {
        boolean questionsAnswered = StringUtils.isNotEmpty(userDetails.getUserEmail()) && StringUtils.isNotEmpty(userDetails.getUserPassword()) && StringUtils.isNotEmpty(userDetails.getUserFirstName())
                && StringUtils.isNotEmpty(userDetails.getUserLastName()) && StringUtils.isNotEmpty(userDetails.getUserName()) && StringUtils.isNotEmpty(confPassword) && confPassword.equals(userDetails.getUserPassword());
        boolean isEmailValid = Pattern.matches(CommonRegexUtil.EMAIL, userDetails.getUserEmail());

        if (!questionsAnswered) {
            setErrorMessage(EMPTY_FIELD);
            return;
        }
        if (!isEmailValid) {
            setErrorMessage(LOGIN_FAILED);
            return;
        }
        getDatabaseServiceClient().createNewUser(userDetails);
    }
}
