package com.example.educationapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.educationapplication.R;

public class CreateClass extends AppCompatActivity {

    Button createClassButton;
    ImageButton createClassBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_class);

        createClassBackButton = findViewById(R.id.createclass_backbutton);

        createClassButton = findViewById(R.id.createClassBtn);
        createClassBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}