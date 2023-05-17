package com.example.educationapplication.views;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.educationapplication.R;
import com.example.educationapplication.viewmodels.CommentRVAdapter;
import com.example.educationapplication.viewmodels.DiscussionAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataObjects.CommentDto;
import dataObjects.DiscussionDto;


public class DiscussionPage extends AppCompatActivity {
    private List<CommentDto> mData = new ArrayList<>();
    private CommentRVAdapter commentRVAdapter;
    private DiscussionDto selectedItem;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_page);

        selectedItem = (DiscussionDto) getIntent().getSerializableExtra("discussion");

        Button postComment = findViewById(R.id.postComment);
        ImageButton discBackBtn = findViewById(R.id.discussionBackButton);

        TextView discTitleTextView = findViewById(R.id.discTitleTextView);
        TextView discussionTextView = findViewById(R.id.discussionTextView);
        TextView authorDiscTextView = findViewById(R.id.authorDiscTextView);
        TextView timestampDiscTextView = findViewById(R.id.timestampDiscTextView);

        discTitleTextView.setText(selectedItem.getTitle());
        discussionTextView.setText(selectedItem.getContent());
        authorDiscTextView.setText((selectedItem.getAuthor()));
        timestampDiscTextView.setText(selectedItem.getTimestamp().toString());

        RecyclerView commentRecyclerView = findViewById(R.id.commentRecyclerView);
        commentRVAdapter = new CommentRVAdapter(this, mData);
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentRecyclerView.setAdapter(commentRVAdapter);


        discBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText commentText = findViewById(R.id.commentText);
                String content = commentText.getText().toString();
                String author = "Current User"; // Replace with your user authentication logic
                Date timestamp = new Date();

                // Create a new comment object
                CommentDto comment = new CommentDto(content, author, timestamp);

                // Add the comment to the list
                mData.add(comment);

                // Notify the adapter about the new comment
                commentRVAdapter.notifyItemInserted(mData.size() - 1);

                // Clear the input fields
                commentText.setText("");
                hideKeyboard(DiscussionPage.this);
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