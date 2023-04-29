package com.example.educationapplication.integration.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import dataObjects.User;

public class FirebaseWaddleDatabaseServiceClient implements WaddleDatabaseServiceClient {

    final private FirebaseDatabase database;
    final private FirebaseFirestore firestore;
    final private FirebaseAuth mAuth;

    public FirebaseWaddleDatabaseServiceClient() {
        database = FirebaseDatabase.getInstance();
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }


    private User currentUser = null;

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
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
                            User user = dataSnapshot.getValue(User.class);
                            Log.d("TAG", user.getEmail());
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
    public User getUser(String email, String password) {
        return null;
    }

    @Override
    public void createNewUser(String email, String password) {

    }



}
