package com.example.educationapplication.util.views;

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
import com.example.educationapplication.viewmodels.UserViewModel;
import com.example.educationapplication.search.dataObjects.CourseDto;
import com.example.educationapplication.search.dataObjects.CustomOnCompleteListener;

public class ConfirmJoinCourse extends AppCompatActivity {
    private CourseDto selectedCourse;
    TextView courseName;
    Button cancel;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_join_course);

        // Set up data binding for the activity
        ActivityConfirmJoinCourseBinding confirmJoinCourseBinding = DataBindingUtil.setContentView(this, R.layout.activity_confirm_join_course);
        confirmJoinCourseBinding.setViewModel(new UserViewModel());

        // Get references to views in the layout
        courseName = findViewById(R.id.confirmjoinclasscode);
        confirm = findViewById(R.id.confirmjoincoursebutton);
        cancel = findViewById(R.id.canceljoincoursebutton);

        // Retrieve the selected course from the intent
        selectedCourse = (CourseDto) getIntent().getSerializableExtra("course");
        courseName.setText(selectedCourse.getCourseName());

        // Set a click listener for the confirm button
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the joinCourse method on the view model and pass the selected course name
                confirmJoinCourseBinding.getViewModel().joinCourse(selectedCourse.getCourseName(), new CustomOnCompleteListener() {
                    @Override
                    public void onComplete() {
                        // Hide the keyboard and finish the activity when the operation is complete
                        hideKeyboard(ConfirmJoinCourse.this);
                        finish();
                    }
                });

                // Display a toast message to indicate successful joining of the course
                Toast.makeText(getApplicationContext(), "You have been added to " + selectedCourse.getCourseName() + "!", Toast.LENGTH_SHORT).show();
            }
        });

        // Set a click listener for the cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the activity when the cancel button is clicked
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
