package com.example.educationapplication.views.Fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.educationapplication.R;
import com.example.educationapplication.viewmodels.CommentRVAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataObjects.CommentDto;


public class DiscussionPage extends AppCompatActivity {
    List<CommentDto> mData = new ArrayList<>();
    CommentRVAdapter commentRVAdapter;
    private CommentDto selectedItem;
    View view;
    Button postComment;
    EditText commentText;
    RecyclerView commentRecyclerView;
    TextView contentTextView, authorTextView, timestampTextView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_page);

        selectedItem = getIntent().getParcelableExtra("comment");

        commentRecyclerView = findViewById(R.id.commentRecyclerView);
        commentRVAdapter = new CommentRVAdapter(this, mData);

        commentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentRecyclerView.setAdapter(commentRVAdapter);

        postComment = findViewById(R.id.postButton);
        commentText = findViewById(R.id.commentText);
        commentRecyclerView = findViewById(R.id.commentRecyclerView);
        contentTextView = findViewById(R.id.commentTextView);
        authorTextView = findViewById(R.id.authorCommentTextView);
        timestampTextView = findViewById(R.id.timestampCommentTextView);

        contentTextView.setText(selectedItem.getComment());
        authorTextView.setText((selectedItem.getAuthor()));
        timestampTextView.setText((CharSequence) selectedItem.getTimestamp());

        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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