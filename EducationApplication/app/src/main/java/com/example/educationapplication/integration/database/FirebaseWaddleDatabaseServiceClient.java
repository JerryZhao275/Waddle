package com.example.educationapplication.integration.database;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import dataObjects.AdminUserDto;
import dataObjects.CustomOnCompleteListener;
import dataObjects.LoginUserDto;
import dataObjects.TeacherUserDto;
import dataObjects.UserDto;
import dataObjects.UserType;

public class FirebaseWaddleDatabaseServiceClient implements WaddleDatabaseServiceClient {
    final private FirebaseDatabase database;
    final private FirebaseFirestore firestore;
    final private FirebaseAuth mAuth;
    private String userType;
    public FirebaseWaddleDatabaseServiceClient() {
        database = FirebaseDatabase.getInstance();
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    private LoginUserDto currentUser = new LoginUserDto("","");
    private UserDto userDetails;

    @Override
    public LoginUserDto getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setUserDetails(CustomOnCompleteListener listener) {
        DocumentReference docRef = firestore.collection("Users").document(mAuth.getCurrentUser().getUid());
        database.getReference("Users").child(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot document1 = task.getResult();
                System.out.println(document1.getValue());
                userType = document1.getValue().toString();
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()) {

                            DocumentSnapshot document = task.getResult();
                            userDetails = UserTypeFactory.createUser(userType, document);
                            listener.onComplete();
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
            }
        });
    }

    public void setCurrentUser(LoginUserDto currentUser) {
        this.currentUser = currentUser;
    }
    public interface OnCompleteCallback{
        void onComplete(boolean success);
    }
    @Override
    public boolean signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser fUser = mAuth.getCurrentUser();
                            LoginUserDto user = new LoginUserDto(fUser.getUid(), fUser.getEmail());
                            System.out.println(user);
                            setCurrentUser(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            System.out.println(getCurrentUser());
                            setCurrentUser(null);
                        }
                    }

                });
        return false;
    }
    public UserDto getUserDetails(){
        return userDetails;
    }
    @Override
    public void signOut() {

    }

    @Override
    public LoginUserDto getUser(String email, String password) {
        System.out.println(mAuth.getCurrentUser());
        return null;
    }

    @Override
    public void createNewUser(UserDto user, String password) {
        mAuth.createUserWithEmailAndPassword(user.getUserEmail(), password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    user.setUserId(currentUser.getUid());
                    if(user instanceof AdminUserDto) {
                        database.getReference("Users").child(currentUser.getUid()).setValue(UserType.ADMIN);
                    }
                    else if(user instanceof TeacherUserDto){
                        database.getReference("Users").child(currentUser.getUid()).setValue(UserType.TEACHER);
                    }
                    else{
                        database.getReference("Users").child(currentUser.getUid()).setValue(UserType.STUDENT);
                    }
                    firestore.collection("Users").document(currentUser.getUid()).set(user);

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                }
            }
        });
    }



}
