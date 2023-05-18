package com.example.educationapplication.viewmodels;
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
import com.example.educationapplication.search.dataObjects.*;

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

    /**
     * Get the email address.
     *
     * @return The email address.
     */
    @Bindable
    public String getEmail() {
        return login.getEmail();
    }

    /**
     * Set the email address.
     *
     * @param email The email address to set.
     */
    @Bindable
    public void setEmail(String email) {
        login.setEmail(email);
        notifyPropertyChanged(BR.email);
    }

    /**
     * Get the password.
     *
     * @return The password.
     */
    @Bindable
    public String getPassword() {
        return login.getPassword();
    }

    /**
     * Set the password.
     *
     * @param password The password to set.
     */
    @Bindable
    public void setPassword(String password) {
        login.setPassword(password);
        notifyPropertyChanged(BR.password);
    }

    /**
     * Get the error message.
     *
     * @return The error message.
     */
    @Bindable
    public String getErrorMessage() {
        return login.getErrorMessage();
    }

    /**
     * Set the error message.
     *
     * @param errorMessage The error message to set.
     */
    @Bindable
    public void setErrorMessage(String errorMessage) {
        login.setErrorMessage(errorMessage);
        notifyPropertyChanged(BR.errorMessage);
    }

    /**
     * Check if the user is authorized.
     *
     * @return True if the user is authorized, false otherwise.
     */
    public boolean isAuthorised() {
        return authorised;
    }

    /**
     * Set the authorization status.
     *
     * @param authorised The authorization status to set.
     */
    public void setAuthorised(boolean authorised) {
        this.authorised = authorised;
    }

    /**
     * Performs the login operation.
     *
     * @param listener The listener to be called when the login operation is complete.
     */
    public void login(CustomOnCompleteListener listener) {
        boolean questionsAnswered = StringUtils.isNotEmpty(login.getEmail()) && StringUtils.isNotEmpty(login.getPassword());
        boolean isEmailValid = Pattern.matches(CommonRegexUtil.EMAIL, login.getEmail());

        // Check if all fields are filled
        if (!questionsAnswered) {
            setErrorMessage(EMPTY_FIELD);
            setAuthorised(false);
            return;
        }

        // Check if the email is valid
        if (!isEmailValid) {
            setErrorMessage(LOGIN_FAILED);
            setAuthorised(false);
            return;
        }

        // Perform the sign-in operation
        getDatabaseServiceClient().signIn(login.getEmail(), login.getPassword(), () -> {
            if (getDatabaseServiceClient().getCurrentUser() == null) {
                // User authentication failed
                setErrorMessage(INVALID_USER);
                setAuthorised(false);
                return;
            }

            // User authentication succeeded
            setAuthorised(true);
            setErrorMessage(null);
            listener.onComplete();
        });
    }
}