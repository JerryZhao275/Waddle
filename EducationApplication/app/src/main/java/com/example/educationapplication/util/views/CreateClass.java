package com.example.educationapplication.util.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.educationapplication.R;
import com.example.educationapplication.databinding.CreateClassBinding;
import com.example.educationapplication.viewmodels.CreateClassViewModel;

import com.example.educationapplication.search.dataObjects.CustomOnCompleteListener;
import com.example.educationapplication.search.dataObjects.TeacherUserDto;

public class CreateClass extends AppCompatActivity {

    Button createClassButton;
    ImageButton createClassBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_class);
        CreateClassBinding createClassBinding = DataBindingUtil.setContentView(this, R.layout.create_class);
        createClassBinding.setViewModel(new CreateClassViewModel());
        TeacherUserDto user = (TeacherUserDto)getIntent().getSerializableExtra("teacher");
        createClassBinding.getViewModel().setTeacher(user);
        createClassBackButton = findViewById(R.id.createclass_backbutton);

        createClassButton = findViewById(R.id.createClassBtn);

        createClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createClassBinding.getViewModel().createCourse(new CustomOnCompleteListener() {
                    @Override
                    public void onComplete() {
                        finish();
                    }
                });
            }
        });
        createClassBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}