package com.example.educationapplication.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.educationapplication.R;
import com.example.educationapplication.databinding.SignupBinding;
import com.example.educationapplication.viewmodels.SignUpViewModel;

public class SignupView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SignupBinding signupBinding = DataBindingUtil.setContentView(this, R.layout.signup);
        signupBinding.setViewModel(new SignUpViewModel(false));
        signupBinding.setOnSignup(()->{
            signupBinding.getViewModel().createUser();
            if(signupBinding.getViewModel().getErrorMessage().equals("")) {
                setContentView(R.layout.activity_main);
            }
        });
        signupBinding.setOnLogin(()->{
            Intent intent = new Intent(getApplicationContext(), LoginView.class);
            startActivity(intent);
        });
        signupBinding.executePendingBindings();
    }

}