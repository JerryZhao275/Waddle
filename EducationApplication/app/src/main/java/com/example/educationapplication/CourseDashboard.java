package com.example.educationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class CourseDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_dashboard);

        FloatingActionButton addclassbtn = findViewById(R.id.addclassbtn);
        TextInputLayout codeinputlayout = findViewById(R.id.codeinputlayout);
        EditText codeinput = findViewById(R.id.codeinput);
        codeinput.setVisibility(View.GONE);
        codeinputlayout.setVisibility(View.GONE);

        addclassbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeinput.setVisibility(View.VISIBLE);
                codeinputlayout.setVisibility(View.VISIBLE);
            }
        });
    }
}