package com.example.educationapplication.views;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.educationapplication.R;
import com.example.educationapplication.databinding.ActivityUserPageBinding;
import com.example.educationapplication.viewmodels.UserViewModel;
import java.util.ArrayList;
import java.util.List;
import dataObjects.CustomOnCompleteListener;
import dataObjects.UserDto;

public class UserPage extends AppCompatActivity {
    private UserDto selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        // Set up data binding for the activity
        ActivityUserPageBinding userpageBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_page);
        userpageBinding.setViewModel(new UserViewModel());

        // Get the selected user passed from the previous activity
        selectedUser = (UserDto) getIntent().getSerializableExtra("user");

        // Fetch additional details of the selected user
        userpageBinding.getViewModel().fetchOtherUserDetails(selectedUser, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                // Update the selected user with the fetched details
                selectedUser = userpageBinding.getViewModel().getOtherUserDetails();

                // Set the user details in the corresponding TextViews
                TextView username = findViewById(R.id.user_userName);
                TextView email = findViewById(R.id.user_Email);
                TextView courses = findViewById(R.id.userCourseList);
                username.setText(selectedUser.getUserFirstName() + " " + selectedUser.getUserLastName());
                email.setText(selectedUser.getUserEmail());
                courses.setText(convertListToString(selectedUser.getCourses()));
            }
        });

        // Set up the back button click listener
        ImageButton backBtn = findViewById(R.id.userPageBackButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    // Convert a list of courses to a string, separated by newlines
    public static String convertListToString(List<String> courses) {
        StringBuilder stringBuilder = new StringBuilder();
        if(courses == null){
            courses = new ArrayList<>();
        }
        for (String item : courses) {
            stringBuilder.append(item).append("\n");
        }
        return stringBuilder.toString();
    }
}
