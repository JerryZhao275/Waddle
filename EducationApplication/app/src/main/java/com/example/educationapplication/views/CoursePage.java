package com.example.educationapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.educationapplication.R;
import com.example.educationapplication.views.Fragment.DashboardFragment;

import dataObjects.CourseDto;

public class CoursePage extends AppCompatActivity {
    private CourseDto selectedCourse;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);


        selectedCourse = getIntent().getParcelableExtra("course");

        TextView courseN = findViewById(R.id.classpage_classname);
        TextView courseDesc = findViewById(R.id.classpage_classdescription);
        ImageButton backBtn = findViewById(R.id.classpage_backbutton);

        courseN.setText(selectedCourse.getCourseName());
        courseDesc.setText((selectedCourse.getCourseDescription()));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }
}