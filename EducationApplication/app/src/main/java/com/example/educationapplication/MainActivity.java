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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataObjects.AdminUserDto;
import dataObjects.UserDto;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private FirebaseFirestore db;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        //This is added just to check out fire base functionality. Comment this out for production.
        List<UserDto> users = new ArrayList<>();
        users.add(new AdminUserDto(users.size()+1, "Admin", "Admin", "Admin", "admin@admin.au", "admin1234", ""));
        Map<String, List<UserDto>> data = new HashMap<>();
        data.put("User", users);
        db.collection("Users")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
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
        else {
            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                setContentView(R.layout.activity_main);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "The email or password you entered was incorrect!.",
                                        Toast.LENGTH_SHORT).show();
                                password.setText("");
                            }
                        }
                    });
        }
        //Some code for logging the user in/checking if the user exists in the firebase or not
    }


    public void signup(View view) {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }
}