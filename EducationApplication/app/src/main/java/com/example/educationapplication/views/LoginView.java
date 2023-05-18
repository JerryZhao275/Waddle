package com.example.educationapplication.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.educationapplication.R;
import com.example.educationapplication.dataInstances.DataReader;
import com.example.educationapplication.databinding.LoginBinding;
import com.example.educationapplication.viewmodels.LoginViewModel;
import dataObjects.CustomOnCompleteListener;

public class LoginView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up data binding for the activity
        LoginBinding loginBinding = DataBindingUtil.setContentView(this, R.layout.login);
        loginBinding.setViewModel(new LoginViewModel());

        // Set a callback for the login button click event
        loginBinding.setOnLogin(() -> {
            loginBinding.getViewModel().login(new CustomOnCompleteListener() {
                @Override
                public void onComplete() {
                    // Call the method to change to the homepage based on the authorization status
                    changeToHomepage(loginBinding.getViewModel().isAuthorised());
                }
            });
        });

        // Set a callback for the signup button click event
        loginBinding.setOnSignup(() -> {
            // Finish the activity and start the SignupView activity
            finish();
            Intent intent = new Intent(getApplicationContext(), SignupView.class);
            startActivity(intent);
        });

        // Ensure all pending bindings are executed
        loginBinding.executePendingBindings();

        DataReader newr = new DataReader();
        newr.getUserInfo(LoginView.this);

    }

    protected void changeToHomepage(boolean isAuthorised) {
        // Check if the user is authorized
        if (isAuthorised) {
            // Finish the current activity and start the MainActivity
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}