package com.example.educationapplication.integration.database;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import dataObjects.LoginUserDto;
import dataObjects.UserDto;

public class FirebaseWaddleDatabaseServiceClient implements WaddleDatabaseServiceClient {
    final private FirebaseDatabase database;
    final private FirebaseFirestore firestore;
    final private FirebaseAuth mAuth;

    public FirebaseWaddleDatabaseServiceClient() {
        database = FirebaseDatabase.getInstance();
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    private LoginUserDto currentUser = null;

    @Override
    public LoginUserDto getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(LoginUserDto currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public boolean signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    System.out.println(uid);
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                    System.out.println(rootRef);
                    DatabaseReference uidRef = rootRef.child("User").child(uid);
                    System.out.println(uidRef);
                    ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            LoginUserDto user = dataSnapshot.getValue(LoginUserDto.class);
                            Log.d("TAG", user.getLoginUserEmail());
                            setCurrentUser(user);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.d("TAG", databaseError.getMessage()); //Don't ignore errors!
                        }
                    };
                    uidRef.addListenerForSingleValueEvent(valueEventListener);
                });
        return false;
    }

    @Override
    public void signOut() {

    }

    @Override
    public LoginUserDto getUser(String email, String password) {
        return null;
    }

    @Override
    public void createNewUser(UserDto user) {
        mAuth.createUserWithEmailAndPassword(user.getUserEmail(), user.getUserPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    user.setUserId(currentUser.getUid());
                    firestore.collection("Users").document(currentUser.getUid()).set(user);

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                }
            }
        });
    }



}
