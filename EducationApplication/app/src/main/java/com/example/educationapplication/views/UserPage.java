package com.example.educationapplication.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.educationapplication.R;
import com.example.educationapplication.databinding.ActivityUserPageBinding;
import com.example.educationapplication.databinding.FragmentSearchBinding;
import com.example.educationapplication.databinding.LoginBinding;
import com.example.educationapplication.viewmodels.LoginViewModel;
import com.example.educationapplication.viewmodels.UserViewModel;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

import dataObjects.CourseDto;
import dataObjects.CustomOnCompleteListener;
import dataObjects.UserDto;

public class UserPage extends AppCompatActivity {
    private UserDto selectedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        ActivityUserPageBinding userpageBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_page);
        userpageBinding.setViewModel(new UserViewModel());

        selectedUser = (UserDto) getIntent().getSerializableExtra("user");
        userpageBinding.getViewModel().fetchOtherUserDetails(selectedUser, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                selectedUser = userpageBinding.getViewModel().getOtherUserDetails();
                TextView username = findViewById(R.id.user_userName);
                TextView email = findViewById(R.id.user_Email);
                username.setText(selectedUser.getUserFirstName() + " " + selectedUser.getUserLastName());
                email.setText(selectedUser.getUserEmail());
            }
        });


        ImageButton backBtn = findViewById(R.id.userPageBackButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public static String convertListToString(List<String> courses) {
        StringBuilder stringBuilder = new StringBuilder();
        if(courses==null){
            courses = new ArrayList<>();
        }
        int i = 0;
        for (String item : courses) {
            if (i != 0) stringBuilder.append(item).append("\n");
            i++;
        }
        return stringBuilder.toString();
    }
}