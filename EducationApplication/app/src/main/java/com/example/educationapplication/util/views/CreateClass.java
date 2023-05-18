package com.example.educationapplication.views;

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

        // Set up data binding with the layout
        CreateClassBinding createClassBinding = DataBindingUtil.setContentView(this, R.layout.create_class);
        createClassBinding.setViewModel(new CreateClassViewModel());

        // Get the teacher user from the intent
        TeacherUserDto user = (TeacherUserDto) getIntent().getSerializableExtra("teacher");

        // Set the teacher user in the view model
        createClassBinding.getViewModel().setTeacher(user);

        // Get references to views in the layout
        createClassBackButton = findViewById(R.id.createclass_backbutton);
        createClassButton = findViewById(R.id.createClassBtn);

        // Set a click listener for the create class button
        createClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the createCourse method in the view model to create a new course
                createClassBinding.getViewModel().createCourse(new CustomOnCompleteListener() {
                    @Override
                    public void onComplete() {
                        // Finish the activity when the course creation is complete
                        finish();
                    }
                });
            }
        });

        // Set a click listener for the back button
        createClassBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity to navigate back
                finish();
            }
        });
    }
}