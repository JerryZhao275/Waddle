package com.example.educationapplication.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.fragment.app.FragmentTransaction;

import com.example.educationapplication.BR;
import com.example.educationapplication.R;
import com.example.educationapplication.integration.database.FirebaseWaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;
import com.example.educationapplication.views.Fragment.DashboardFragment;
import com.google.firebase.auth.FirebaseAuth;

import dataObjects.AdminUserDto;
import dataObjects.CustomOnCompleteListener;
import dataObjects.StudentUserDto;
import dataObjects.UserDto;

public class UserViewModel extends BaseObservable {
    WaddleDatabaseConfiguration config;
    WaddleDatabaseServiceClient databaseServiceClient;
    UserDto user;
    String userFirstName = "";
    String email = "";
    public UserViewModel() {
        config = ConfigurationManager.configInstance();
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
        databaseServiceClient.setUserDetails(new CustomOnCompleteListener(){
            @Override
            public void onComplete() {
                user = databaseServiceClient.getUserDetails();
                System.out.println(user.getUserName()+" "+user.getUserEmail());
                setFirstName(user.getUserFirstName());
                setEmail(user.getUserEmail());
                System.out.println(user instanceof StudentUserDto);
            }
        });

    }
    @Bindable
    public String getEmail() {
        return email;
    }

    @Bindable
    public String getFirstName() {
        return userFirstName;
    }
    @Bindable
    public void setFirstName(String firstName) {
        userFirstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }


    @Bindable
    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }
}
