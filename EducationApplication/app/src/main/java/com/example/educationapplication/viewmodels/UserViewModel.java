package com.example.educationapplication.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.educationapplication.BR;
import com.example.educationapplication.integration.database.FirebaseWaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;
import com.google.firebase.auth.FirebaseAuth;

import dataObjects.UserDto;

public class UserViewModel extends BaseObservable {
    WaddleDatabaseConfiguration config;
    WaddleDatabaseServiceClient databaseServiceClient;
    UserDto user;

    public UserViewModel() {
        config = ConfigurationManager.configInstance();
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
        user = databaseServiceClient.getUserDetails();
    }
    @Bindable
    public String getEmail() {
        System.out.println(user.getUserEmail());
        return user.getUserEmail();
    }

    @Bindable
    public String getFirstName() {
        System.out.println(user.getUserFirstName());
        return user.getUserFirstName();
    }
    @Bindable
    public void setFirstName(String firstName) {
        System.out.println(firstName);
        user.setUserFirstName(firstName);
        notifyPropertyChanged(BR.firstName);
    }


    @Bindable
    public void setEmail(String email) {
        System.out.println(email);
        user.setUserEmail(email);
        notifyPropertyChanged(BR.email);
    }
}
