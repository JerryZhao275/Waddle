package com.example.educationapplication.util.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.educationapplication.R;
import com.example.educationapplication.util.StringUtils;
import com.example.educationapplication.viewmodels.DiscussionAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.educationapplication.search.dataObjects.CourseDto;
import com.example.educationapplication.search.dataObjects.DiscussionDto;

public class CoursePage extends AppCompatActivity {
    private CourseDto selectedCourse;

    private List<DiscussionDto> discussions = new ArrayList<>();
    private DiscussionAdapter discussionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);

        // Retrieve the selected course from the intent
        selectedCourse = (CourseDto) getIntent().getSerializableExtra("course");

        // Get references to the views in the layout
        TextView courseN = findViewById(R.id.classpage_classname);
        TextView courseDesc = findViewById(R.id.classpage_classdescription);
        ImageButton backBtn = findViewById(R.id.classpage_backbutton);

        // Set the course name and description in the text views
        courseN.setText(selectedCourse.getCourseName());
        courseDesc.setText(selectedCourse.getCourseDescription());

        // Set up the discussion recycler view and adapter
        RecyclerView discussionRecyclerView = findViewById(R.id.discussionRecyclerView);
        discussionAdapter = new DiscussionAdapter(this, discussions, selectedCourse);
        discussionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        discussionRecyclerView.setAdapter(discussionAdapter);

        // Set a click listener for the back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go back to the previous activity
                onBackPressed();
                hideKeyboard(CoursePage.this);
            }
        });

        // Set a click listener for the post button
        Button postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get references to the input fields
                EditText titleEditText = findViewById(R.id.titleEditText);
                EditText contentEditText = findViewById(R.id.contentEditText);

                // Get user input
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();
                if (!StringUtils.isEmpty(title) && !StringUtils.isEmpty(content)) {
                    String author = "Current User"; // Replace with your user authentication logic
                    Date timestamp = new Date();

                    // Create a new discussion object
                    DiscussionDto discussion = new DiscussionDto(title, content, author, timestamp);

                    // Add the discussion to the list
                    discussionAdapter.addDiscussion(discussion);
                }

                // Clear the input fields
                titleEditText.setText("");
                contentEditText.setText("");
                hideKeyboard(CoursePage.this);
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