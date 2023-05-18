package com.example.educationapplication.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.educationapplication.R;
import com.example.educationapplication.databinding.SignupBinding;
import com.example.educationapplication.viewmodels.SignUpViewModel;

import dataObjects.CustomOnCompleteListener;

public class SignupView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SignupBinding signupBinding = DataBindingUtil.setContentView(this, R.layout.signup);
        signupBinding.setViewModel(new SignUpViewModel());
        signupBinding.setOnSignup(()->{
            signupBinding.getViewModel().createUser(new CustomOnCompleteListener() {
                @Override
                public void onComplete() {
                    finish();
                    Toast toast = Toast.makeText(getApplicationContext(), "Account successfully created!", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(getApplicationContext(), LoginView.class);
                    startActivity(intent);
                }
            });
        });
        signupBinding.setOnLogin(()->{
            finish();
            Intent intent = new Intent(getApplicationContext(), LoginView.class);
            startActivity(intent);
        });
        signupBinding.executePendingBindings();
    }

}