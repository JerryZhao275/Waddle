package com.example.educationapplication.util.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.educationapplication.R;
import com.example.educationapplication.databinding.SignupBinding;
import com.example.educationapplication.viewmodels.SignUpViewModel;

public class SignupView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up data binding for the activity
        SignupBinding signupBinding = DataBindingUtil.setContentView(this, R.layout.signup);
        signupBinding.setViewModel(new SignUpViewModel());

        // Set a callback for the signup button click event
        signupBinding.setOnSignup(() -> {
            // Create a user using the SignUpViewModel
            signupBinding.getViewModel().createUser();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Check if there was an error during user creation
            if (signupBinding.getViewModel().getErrorMessage().equals("")) {
                // If no error, finish the activity and show a toast message
                finish();
                Toast toast = Toast.makeText(getApplicationContext(), "Account successfully created!", Toast.LENGTH_SHORT);
                toast.show();

                // Start the LoginView activity
                Intent intent = new Intent(getApplicationContext(), LoginView.class);
                startActivity(intent);
            }
        });

        // Set a callback for the login button click event
        signupBinding.setOnLogin(() -> {
            // Finish the activity and start the LoginView activity
            finish();
            Intent intent = new Intent(getApplicationContext(), LoginView.class);
            startActivity(intent);
        });

        // Ensure all pending bindings are executed
        signupBinding.executePendingBindings();
    }
}
