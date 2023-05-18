package com.example.educationapplication.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.educationapplication.R;
import com.example.educationapplication.databinding.LoginBinding;
import com.example.educationapplication.viewmodels.LoginViewModel;

import com.example.educationapplication.dataInstances.DataReader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dataObjects.CustomOnCompleteListener;

public class LoginView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
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
