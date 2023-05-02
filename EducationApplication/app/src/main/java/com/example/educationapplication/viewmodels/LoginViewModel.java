package com.example.educationapplication.viewmodels;

import static androidx.databinding.DataBindingUtil.setContentView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.educationapplication.BR;
import com.example.educationapplication.R;
import com.example.educationapplication.integration.database.FirebaseWaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;
import com.example.educationapplication.model.LoginModel;
import com.example.educationapplication.util.CommonRegexUtil;
import com.example.educationapplication.util.StringUtils;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import dataObjects.UserDto;

public class LoginViewModel extends BaseObservable {
    private final boolean useMock;
    private final WaddleDatabaseConfiguration config;
    private final WaddleDatabaseServiceClient databaseServiceClient;
    private final LoginModel login = new LoginModel("", "");

    private boolean authorised = false;

    private final static String LOGIN_FAILED = "Invalid email. Check your spelling and try again.";
    public final static String INVALID_USER = "Could not find the user specified. Check your spelling and try again.";
    private final static String EMPTY_FIELD = "Please fill out all fields.";

    public LoginViewModel(boolean useMock) {
        this.useMock = useMock;
        config = ConfigurationManager.configInstance(this.useMock);
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
        System.out.println(databaseServiceClient.getCurrentUser());
    }

    public WaddleDatabaseServiceClient getDatabaseServiceClient() {
        return databaseServiceClient;
    }

    @Bindable
    public String getEmail() {
        System.out.println(login.getEmail());
        return login.getEmail();
    }

    @Bindable
    public void setEmail(String email) {
        System.out.println(email);
        login.setEmail(email);
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPassword() {
        return login.getPassword();
    }

    @Bindable
    public void setPassword(String password) {
        login.setPassword(password);
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getErrorMessage() {
        return login.getErrorMessage();
    }

    @Bindable
    public void setErrorMessage(String errorMessage) {
        login.setErrorMessage(errorMessage);
        notifyPropertyChanged(BR.errorMessage);
    }

    public boolean isAuthorised() {
        return authorised;
    }

    public void setAuthorised(boolean authorised) {
        this.authorised = authorised;
    }

    public void login() {
        boolean questionsAnswered = StringUtils.isNotEmpty(login.getEmail()) && StringUtils.isNotEmpty(login.getPassword());
        boolean isEmailValid = Pattern.matches(CommonRegexUtil.EMAIL, login.getEmail());

        if (!questionsAnswered) {
            setErrorMessage(EMPTY_FIELD);
            setAuthorised(false);
            return;
        }
        if (!isEmailValid) {
            setErrorMessage(LOGIN_FAILED);
            setAuthorised(false);
            return;
        }
        getDatabaseServiceClient().signIn(login.getEmail(), login.getPassword());

        if (getDatabaseServiceClient().getCurrentUser() == null) {
            setErrorMessage(INVALID_USER);
            setAuthorised(false);
            return;
        }
        setAuthorised(true);
    }

}
