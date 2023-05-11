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
        signupBinding.setViewModel(new SignUpViewModel());
        signupBinding.setOnSignup(()->{
            signupBinding.getViewModel().createUser();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(signupBinding.getViewModel().getErrorMessage().equals("")) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        signupBinding.setOnLogin(()->{
            finish();
            Intent intent = new Intent(getApplicationContext(), LoginView.class);
            startActivity(intent);
        });
        signupBinding.executePendingBindings();
    }

}