package com.example.educationapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataObjects.AdminUserDto;
import dataObjects.UserDto;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private FirebaseFirestore db;
    List<UserDto> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        db = FirebaseFirestore.getInstance();
        //This is added just to check out fire base functionality. Comment this out for production.

        /*users.add(new AdminUserDto(users.size()+1, "Admin", "Admin", "Admin", "admin@admin.au", "admin1234", ""));
        users.add(new AdminUserDto(users.size()+1, "Admin2", "Admin2", "Admin2", "admin2@admin.au", "admin1234", ""));
        Map<String, List<UserDto>> data = new HashMap<>();
        data.put("User", users);
        db.collection("UserCollection").document("Users")
                .set(data);*/

        DocumentReference docRef = db.collection("UserCollection").document("Users");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        List<UserDto> x = (ArrayList<UserDto>) document.getData().get("User");
                        Log.d(TAG, "DocumentSnapshot data: " + x);

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


        setContentView(R.layout.login);
    }
    @Override
    protected void onStart() {
        super.onStart();

        EditText email = findViewById(R.id.editTextUsername);
        EditText password = findViewById(R.id.editTextPassword);
        email.setText("");
        password.setText("");
    }

    public void login(View view) {
        EditText email = findViewById(R.id.editTextUsername);
        EditText password = findViewById(R.id.editTextPassword);
        if (email.getText().toString().length() == 0 || password.getText().toString().length() == 0) {
            Toast.makeText(MainActivity.this, "Please enter an email/password!", Toast.LENGTH_SHORT).show();
        }
        //Some code for logging the user in/checking if the user exists in the firebase or not
    }

    public void signup(View view) {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }
}
