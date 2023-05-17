package com.example.educationapplication.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.educationapplication.R;
import com.example.educationapplication.databinding.ActivityConfirmJoinCourseBinding;
import com.example.educationapplication.databinding.CreateClassBinding;
import com.example.educationapplication.viewmodels.CreateClassViewModel;
import com.example.educationapplication.viewmodels.UserViewModel;

import dataObjects.CourseDto;
import dataObjects.CustomOnCompleteListener;

public class ConfirmJoinCourse extends AppCompatActivity {
    private CourseDto selectedCourse;
    TextView courseName;
    Button cancel;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_join_course);

        ActivityConfirmJoinCourseBinding confirmJoinCourseBinding = DataBindingUtil.setContentView(this, R.layout.activity_confirm_join_course);
        confirmJoinCourseBinding.setViewModel(new UserViewModel());


        courseName = findViewById(R.id.confirmjoinclasscode);
        confirm = findViewById(R.id.confirmjoincoursebutton);
        cancel = findViewById(R.id.canceljoincoursebutton);


        selectedCourse = (CourseDto) getIntent().getSerializableExtra("course");
        courseName.setText(selectedCourse.getCourseName());

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmJoinCourseBinding.getViewModel().joinCourse(selectedCourse.getCourseName(), new CustomOnCompleteListener() {
                    @Override
                    public void onComplete() {
                        hideKeyboard(ConfirmJoinCourse.this);
                        finish();
                    }
                });
                Toast.makeText(getApplicationContext(), "You have been added to " + selectedCourse.getCourseName() + "!", Toast.LENGTH_SHORT).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        // Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        // If no view currently has focus, create a new one, just so we can grab a window token from it.
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}