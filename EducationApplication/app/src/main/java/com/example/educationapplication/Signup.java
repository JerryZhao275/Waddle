package com.example.educationapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void loginButton(View view) {
        finish();
    }

    public void createAccount(View view) {
        EditText email = findViewById(R.id.signupEmail);
        EditText password = findViewById(R.id.signupPassword);
        EditText firstName = findViewById(R.id.signupFirstName);
        EditText lastName = findViewById(R.id.signupLastName);
        EditText confirmPassword = findViewById(R.id.signupPasswordConfirm);
        EditText uniID = findViewById(R.id.signupUID);

        if (TextUtils.isEmpty(email.getText().toString())
                || TextUtils.isEmpty(password.getText().toString())
                || TextUtils.isEmpty(firstName.getText().toString())
                || TextUtils.isEmpty(lastName.getText().toString())
                || TextUtils.isEmpty(confirmPassword.getText().toString())
                || TextUtils.isEmpty(uniID.getText().toString())) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
        }
        //Add sign in
//        mAuth.createUserWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    // Sign in success, update UI with the signed-in user's information
//                                    Log.d(TAG, "createUserWithEmail:success");
//                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    updateUI(user);
//                                } else {
//                                    // If sign in fails, display a message to the user.
//                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                                    Toast.makeText(Signup.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
//                                    updateUI(null);
//                                }
//                            }
//                        });
    }
}