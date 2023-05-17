package com.example.educationapplication.viewmodels;

import static androidx.databinding.DataBindingUtil.setContentView;

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

import com.example.educationapplication.search.dataObjects.CustomOnCompleteListener;

public class LoginViewModel extends BaseObservable {
    private final WaddleDatabaseConfiguration config;
    private final WaddleDatabaseServiceClient databaseServiceClient;
    private final LoginModel login = new LoginModel("", "");

    private boolean authorised = false;

    private final static String LOGIN_FAILED = "Invalid email. Check your spelling and try again.";
    public final static String INVALID_USER = "Could not find the user specified. Check your spelling and try again.";
    private final static String EMPTY_FIELD = "Please fill out all fields.";

    public LoginViewModel() {
        config = ConfigurationManager.configInstance();
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
        databaseServiceClient.signOut();
    }

    public WaddleDatabaseServiceClient getDatabaseServiceClient() {
        return databaseServiceClient;
    }

    @Bindable
    public String getEmail() {
        return login.getEmail();
    }

    @Bindable
    public void setEmail(String email) {
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

    public void login(CustomOnCompleteListener listener) {
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
        getDatabaseServiceClient().signIn(login.getEmail(), login.getPassword(), () -> {
            if (getDatabaseServiceClient().getCurrentUser() == null) {
                setErrorMessage(INVALID_USER);
                setAuthorised(false);
                return;
            }
            setAuthorised(true);
            setErrorMessage(null);
            listener.onComplete();
        });
    }

}
