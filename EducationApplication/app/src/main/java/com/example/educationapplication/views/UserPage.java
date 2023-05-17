package com.example.educationapplication.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.educationapplication.R;
import com.example.educationapplication.databinding.ActivityUserPageBinding;
import com.example.educationapplication.viewmodels.UserViewModel;
import java.util.ArrayList;
import java.util.List;
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