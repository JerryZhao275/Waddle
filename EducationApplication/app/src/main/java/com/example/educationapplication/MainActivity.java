package com.example.educationapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        setContentView(R.layout.login);
    }
}