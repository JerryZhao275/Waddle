package com.example.educationapplication.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.educationapplication.R;
import com.example.educationapplication.databinding.FragmentDashboardBinding;
import com.example.educationapplication.databinding.LoginBinding;
import com.example.educationapplication.viewmodels.LoginViewModel;

import dataObjects.CustomOnCompleteListener;

public class LoginView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginBinding loginBinding = DataBindingUtil.setContentView(this, R.layout.login);
        loginBinding.setViewModel(new LoginViewModel());
        loginBinding.setOnLogin(()-> {
            loginBinding.getViewModel().login(new CustomOnCompleteListener() {
                @Override
                public void onComplete() {
                    changeToHomepage(loginBinding.getViewModel().isAuthorised());
                }
            });
        });
        loginBinding.setOnSignup(()-> {
            finish();
            Intent intent = new Intent(getApplicationContext(), SignupView.class);
            startActivity(intent);
        });
        loginBinding.setOnSignupTest(()-> {
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
        loginBinding.executePendingBindings();
    }

    protected void changeToHomepage(boolean isAuthorised) {
        if (isAuthorised) {
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}
