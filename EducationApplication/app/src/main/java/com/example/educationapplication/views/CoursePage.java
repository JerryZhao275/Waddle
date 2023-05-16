package com.example.educationapplication.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.educationapplication.R;
import com.example.educationapplication.viewmodels.CourseItemRVAdapater;
import com.example.educationapplication.viewmodels.DiscussionAdapter;
import com.example.educationapplication.views.Fragment.DashboardFragment;
import androidx.appcompat.app.AppCompatActivity;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataObjects.CourseDto;
import dataObjects.DiscussionDto;
import dataObjects.QuizDto;

public class CoursePage extends AppCompatActivity {
    private CourseDto selectedCourse;
    private List<QuizDto> mData = new ArrayList<>();

    private List<DiscussionDto> discussions = new ArrayList<>();
    private DiscussionAdapter discussionAdapter;
    private CourseItemRVAdapater itemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);

        selectedCourse = (CourseDto) getIntent().getSerializableExtra("course");

        TextView courseN = findViewById(R.id.classpage_classname);
        TextView courseDesc = findViewById(R.id.classpage_classdescription);
        ImageButton backBtn = findViewById(R.id.classpage_backbutton);

        courseN.setText(selectedCourse.getCourseName());
        courseDesc.setText((selectedCourse.getCourseDescription()));

        discussions.add(new DiscussionDto("Title 1", "Content 1", "Author 1", new Date()));
        discussions.add(new DiscussionDto("Title 2", "Content 2", "Author 2", new Date()));

        RecyclerView discussionRecyclerView = findViewById(R.id.discussionRecyclerView);
        discussionAdapter = new DiscussionAdapter(discussions);
        discussionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        discussionRecyclerView.setAdapter(discussionAdapter);

        /* Enable to get quiz items instead
        RecyclerView itemRecyclerView = findViewById(R.id.discussionRecyclerView);
        itemAdapter = new CourseItemRVAdapater(getApplicationContext(), mData);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemRecyclerView.setAdapter(itemAdapter);
         */

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Button postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText titleEditText = findViewById(R.id.titleEditText);
                EditText contentEditText = findViewById(R.id.contentEditText);

                // Get user input
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();
                String author = "Current User"; // Replace with your user authentication logic
                Date timestamp = new Date();

                // Create a new discussion object
                DiscussionDto discussion = new DiscussionDto(title, content, author, timestamp);

                // Add the discussion to the list
                discussions.add(discussion);

                // Notify the adapter about the new discussion
                discussionAdapter.notifyItemInserted(discussions.size() - 1);

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