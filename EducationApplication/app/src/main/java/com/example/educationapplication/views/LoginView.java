package com.example.educationapplication.views;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.educationapplication.R;
import com.example.educationapplication.databinding.LoginBinding;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.viewmodels.LoginViewModel;

public class LoginView extends AppCompatActivity {

    private final WaddleDatabaseConfiguration config = ConfigurationManager.configInstance();
    private final WaddleDatabaseServiceClient databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginBinding loginBinding = DataBindingUtil.setContentView(this, R.layout.login);
        loginBinding.setViewModel(new LoginViewModel(databaseServiceClient));
        loginBinding.setOnLogin(()-> {
            loginBinding.getViewModel().login();
            changeToHomepage(loginBinding.getViewModel().isAuthorised());
        });
        loginBinding.setOnSignup(()-> {
            setContentView(R.layout.signup);
        });
        loginBinding.executePendingBindings();
    }

    protected void changeToHomepage(boolean isAuthorised) {
        EditText email = findViewById(R.id.editTextEmail);
        EditText password = findViewById(R.id.editTextPassword);

        if (isAuthorised) {
            setContentView(R.layout.homepage);
        }
    }
}
