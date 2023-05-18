package com.example.educationapplication.util.views;

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
import com.example.educationapplication.util.StringUtils;
import com.example.educationapplication.viewmodels.CommentRVAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.educationapplication.search.dataObjects.CommentDto;
import com.example.educationapplication.search.dataObjects.DiscussionDto;
import com.example.educationapplication.search.dataObjects.UserDto;

public class DiscussionPage extends AppCompatActivity {
    private List<CommentDto> mData = new ArrayList<>();
    private CommentRVAdapter commentRVAdapter;
    private DiscussionDto selectedItem;
    private UserDto user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_page);

        // Retrieve the selected discussion and user data from the intent
        selectedItem = (DiscussionDto) getIntent().getSerializableExtra("discussion");
        user = (UserDto) getIntent().getSerializableExtra("user");

        // Get references to views in the layout
        Button postComment = findViewById(R.id.postComment);
        ImageButton discBackBtn = findViewById(R.id.discussionBackButton);
        TextView discTitleTextView = findViewById(R.id.discTitleTextView);
        TextView discussionTextView = findViewById(R.id.discussionTextView);
        TextView authorDiscTextView = findViewById(R.id.authorDiscTextView);
        TextView timestampDiscTextView = findViewById(R.id.timestampDiscTextView);
        RecyclerView commentRecyclerView = findViewById(R.id.commentRecyclerView);

        // Set the values of the discussion details in the corresponding views
        discTitleTextView.setText(selectedItem.getTitle());
        discussionTextView.setText(selectedItem.getContent());
        authorDiscTextView.setText(selectedItem.getAuthor());
        timestampDiscTextView.setText(selectedItem.getTimestamp().toString());

        // Set up the RecyclerView for displaying comments
        commentRVAdapter = new CommentRVAdapter(this, mData, selectedItem);
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentRecyclerView.setAdapter(commentRVAdapter);

        // Set a click listener for the back button
        discBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Set a click listener for the post comment button
        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the content of the comment from the input field
                EditText commentText = findViewById(R.id.commentText);
                String content = commentText.getText().toString();

                // Validate the comment content
                if (!StringUtils.isEmpty(content)) {
                    // Generate the necessary data for creating a new comment
                    String author = user.getUserFirstName() + " " + user.getUserLastName();
                    String authorId = user.getUserId();
                    String discussionId = selectedItem.getDiscussionID();
                    String commentId = discussionId + "-" + (mData.size() + 1);
                    Date timestamp = new Date();

                    // Create a new comment object
                    CommentDto comment = new CommentDto(commentId, content, timestamp, author, authorId, discussionId);

                    // Add the comment to the list and update the adapter
                    commentRVAdapter.addComment(comment);
                }

                // Clear the input field and hide the keyboard
                commentText.setText("");
                hideKeyboard(DiscussionPage.this);
            }
        });
    }

    // Method to hide the soft keyboard
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}