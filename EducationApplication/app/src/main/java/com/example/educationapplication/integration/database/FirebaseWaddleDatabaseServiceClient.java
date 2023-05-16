package com.example.educationapplication.integration.database;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.educationapplication.search.Exp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataObjects.AdminUserDto;
import dataObjects.CourseDto;
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
    private List<CourseDto> courseList;
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
    public void fetchUserDetails(CustomOnCompleteListener listener) {
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
    public boolean signIn(String email, String password, CustomOnCompleteListener listener) {
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
                        listener.onComplete();
                    }

                });
        return false;
    }

    @Override
    public void fetchUserCourses(CustomOnCompleteListener listener) {
        FieldPath fieldPath = FieldPath.of("teacher", "userId");
        courseList = new ArrayList<>();
        firestore.collection("Courses").whereEqualTo(fieldPath, mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isComplete()){
                    for(DocumentSnapshot documentSnapshot:task.getResult()){
                        CourseDto queryCourse = documentSnapshot.toObject(CourseDto.class);
                        courseList.add(queryCourse);
                    }
                    listener.onComplete();
                }
            }
        });
    }



    @Override
    public List<CourseDto> getUserCourses() {
        return courseList;
    }

    @Override
    public void addCourse(CourseDto course, CustomOnCompleteListener listener) {
        firestore.collection("Courses").document(course.getCourseId().toString()).set(course).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    List<String> newCourseName = new ArrayList<>();
                    if(course.getTeacher().getCourses()!=null){
                        newCourseName = course.getTeacher().getCourses();
                    }
                    newCourseName.add(course.getCourseName());
                    Map<String, List<String>> map = new HashMap<>();
                    map.put("courses", newCourseName);
                    firestore.collection("Users").document(course.getTeacher().getUserId()).update("courses", newCourseName).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            System.out.println("Course added");
                            listener.onComplete();
                        }
                    });

                }
            }
        });
    }
    @Override
    public void synchUsers(CustomOnCompleteListener listener){
        database.getReference("Users").child(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot document1 = task.getResult();
                System.out.println(document1.getValue());
                userType = document1.getValue().toString();
                firestore.collection("Users").whereEqualTo("userId", mAuth.getCurrentUser().getUid()).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        courseList = new ArrayList<>();
                        if(error!=null){
                            System.out.println("Course Error");
                        }
                        else{
                            assert value != null;
                            for(DocumentSnapshot documentSnapshot : value.getDocuments()){
                                System.out.println();
                                userDetails = UserTypeFactory.createUser(userType, documentSnapshot);
                            }
                            listener.onComplete();
                        }
                    }
                });
            }
        });

    }

    @Override
    public void synchCourses(CustomOnCompleteListener listener) {
        FieldPath fieldPath = FieldPath.of("teacher", "userId");
        firestore.collection("Courses").whereEqualTo(fieldPath, mAuth.getCurrentUser().getUid()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                courseList = new ArrayList<>();
                if(error!=null){
                    System.out.println("Course Error");
                }
                else{
                    assert value != null;
                    for(DocumentSnapshot documentSnapshot : value.getDocuments()){
                        CourseDto queryCourse = documentSnapshot.toObject(CourseDto.class);
                        courseList.add(queryCourse);
                    }
                    listener.onComplete();
                }
            }
        });
    }

    public UserDto getUserDetails(){
        return userDetails;
    }
    @Override
    public void signOut() {
        mAuth.signOut();
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
    private List<UserDto> queryUsers = new ArrayList<>();
    private List<CourseDto> queryCourses = new ArrayList<>();
    public void addQueryUser(UserDto user){
        if(queryUsers.stream().noneMatch(u -> u.getUserId().equals(user.getUserId()))){
            queryUsers.add(user);
        }
    }
    public List<UserDto> getQueryUsers(){
        return queryUsers;
    }

    public void addQueryCourse(CourseDto course){
        if(queryCourses.stream().noneMatch(u -> u.getCourseName().equals(course.getCourseName()))){
            queryCourses.add(course);
        }
    }
    public List<CourseDto> getQueryCourses(){
        return queryCourses;
    }


    public void fetchAllUsersForSearch(Exp expression, CustomOnCompleteListener listener){
            List<String> users = new ArrayList<>();
            List<String> emails = new ArrayList<>();
            queryUsers = new ArrayList<>();
            System.out.println(expression.showExpType());
            while(expression.getCurrentValue()!=null){
                if(expression.showExpType().equals("EMAIL")){
                    emails.add(expression.getCurrentValue());
                }
                else{
                    users.add(expression.getCurrentValue());
                }
                expression = expression.getNext();
            }
            Task task1;
            Task task2;
            Task task3;
            Task task4;
            Task<List<QuerySnapshot>> allTasks ;
            List<Task> tasks = new ArrayList<>();
            if(users.size()>0 && emails.size()>0) {
                for(String user:users) {
                    //task1 = firestore.collection("Users").whereIn("userFirstName", users).get();
                    //task2 = firestore.collection("Users").whereIn("userLastName", users).get();
                    //task3 = firestore.collection("Users").whereIn("userName", users).get();
                    tasks.add(firestore.collection("Users").whereGreaterThanOrEqualTo("userFirstName", user).whereLessThan("userFirstName", user+'z').get());
                    tasks.add(firestore.collection("Users").whereGreaterThanOrEqualTo("userLastName", user).whereLessThan("userLastName", user+'z').get());
                    tasks.add(firestore.collection("Users").whereGreaterThanOrEqualTo("userName", user).whereLessThan("userName", user+'z').get());

                }
                for(String email:emails) {
                    tasks.add(firestore.collection("Users").whereGreaterThanOrEqualTo("userEmail", email.toLowerCase()).whereLessThan("userEmail", email.toLowerCase()+'z').get());
                }
                //allTasks = Tasks.whenAllSuccess(task1,task2,task3, task4);
                //tasks.add(task1);
                //tasks.add(task2);
                //tasks.add(task3);
                //tasks.add(task4);
                allTasks = Tasks.whenAllSuccess(tasks);
            }
            else if(users.size()==0 && emails.size()>0){
                for(String email:emails) {
                    tasks.add(firestore.collection("Users").whereGreaterThanOrEqualTo("userEmail", email.toLowerCase()).whereLessThan("userEmail", email.toLowerCase()+'z').get());
                }
                allTasks = Tasks.whenAllSuccess(tasks);
            }
            else if(users.size()>0){
                for(String user:users) {
                    //task1 = firestore.collection("Users").whereIn("userFirstName", users).get();
                    //task2 = firestore.collection("Users").whereIn("userLastName", users).get();
                    //task3 = firestore.collection("Users").whereIn("userName", users).get();
                    tasks.add(firestore.collection("Users").whereGreaterThanOrEqualTo("userFirstName", user).whereLessThan("userFirstName", user+'z').get());
                    tasks.add(firestore.collection("Users").whereGreaterThanOrEqualTo("userLastName", user).whereLessThan("userLastName", user+'z').get());
                    tasks.add(firestore.collection("Users").whereGreaterThanOrEqualTo("userName", user).whereLessThan("userName", user+'z').get());

                }
                allTasks = Tasks.whenAllSuccess(tasks);
            }
            else{
                task1 = firestore.collection("Users").get();
                allTasks = Tasks.whenAllSuccess(task1);
            }

            allTasks.addOnSuccessListener(new OnSuccessListener<List<QuerySnapshot>>() {
                @Override
                public void onSuccess(List<QuerySnapshot> querySnapshots) {

                    if(querySnapshots.stream().anyMatch(q->q.getDocuments().size()>0)) {
                        for (QuerySnapshot queryDocumentSnapshots : querySnapshots) {
                            for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                                String id = queryDocumentSnapshot.get("userId").toString();
                                database.getReference("Users").child(id).get().addOnCompleteListener(
                                        new OnCompleteListener<DataSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                DataSnapshot document1 = task.getResult();
                                                userType = document1.getValue().toString();
                                                System.out.println(userType);
                                                UserDto user = UserTypeFactory.createUser(userType, queryDocumentSnapshot);
                                                System.out.println(user.getUserName());
                                                addQueryUser(user);
                                                System.out.println(queryUsers.size());
                                                listener.onComplete();
                                            }
                                        }
                                );
                            }
                        }
                    }
                    else{
                        firestore.collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                                    String id = queryDocumentSnapshot.get("userId").toString();
                                    System.out.println(id);
                                    database.getReference("Users").child(id).get().addOnCompleteListener(
                                            new OnCompleteListener<DataSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                    DataSnapshot document1 = task.getResult();
                                                    userType = document1.getValue().toString();
                                                    System.out.println(userType);
                                                    UserDto user = UserTypeFactory.createUser(userType, queryDocumentSnapshot);
                                                    System.out.println(user.getUserName());
                                                    addQueryUser(user);
                                                    System.out.println(queryUsers.size());
                                                    listener.onComplete();
                                                }
                                            }
                                    );
                                }
                            }
                        });
                    }


                }
            });
    }

    @Override
    public void fetchAllCoursesForSearch(Exp expression, CustomOnCompleteListener listener) {
        List<String> courseNames = new ArrayList<>();
        List<String> courseDescriptions = new ArrayList<>();
        queryCourses = new ArrayList<>();
        System.out.println(expression.showExpType());
        while(expression.getCurrentValue()!=null){
            if(expression.showExpType().equals("COURSE ID")){
                courseNames.add(expression.getCurrentValue());
            }
            else{
                courseDescriptions.add(expression.getCurrentValue());
            }
            expression = expression.getNext();
        }
        Task task1;
        Task<List<QuerySnapshot>> allTasks ;
        List<Task> tasks = new ArrayList<>();
        if(courseNames.size()>0 && courseDescriptions.size()>0) {
            for(String courseName:courseNames) {
                //task1 = firestore.collection("Users").whereIn("userFirstName", users).get();
                //task2 = firestore.collection("Users").whereIn("userLastName", users).get();
                //task3 = firestore.collection("Users").whereIn("userName", users).get();
                tasks.add(firestore.collection("Courses").whereGreaterThanOrEqualTo("courseName", courseName).whereLessThan("courseName", courseName+'z').get());

            }
            for(String courseDescription:courseDescriptions) {
                tasks.add(firestore.collection("Courses").whereGreaterThanOrEqualTo("courseDescription", courseDescription).whereLessThan("courseDescription", courseDescription+'z').get());
            }
            //allTasks = Tasks.whenAllSuccess(task1,task2,task3, task4);
            //tasks.add(task1);
            //tasks.add(task2);
            //tasks.add(task3);
            //tasks.add(task4);
            allTasks = Tasks.whenAllSuccess(tasks);
        }
        else if(courseNames.size()==0 && courseDescriptions.size()>0){
            for(String courseDescription:courseDescriptions) {
                tasks.add(firestore.collection("Courses").whereGreaterThanOrEqualTo("courseDescription", courseDescription).whereLessThan("courseDescription", courseDescription+'z').get());
            }
            allTasks = Tasks.whenAllSuccess(tasks);
        }
        else if(courseNames.size()>0){
            for(String courseName:courseNames) {
                //task1 = firestore.collection("Users").whereIn("userFirstName", users).get();
                //task2 = firestore.collection("Users").whereIn("userLastName", users).get();
                //task3 = firestore.collection("Users").whereIn("userName", users).get();
                tasks.add(firestore.collection("Courses").whereGreaterThanOrEqualTo("courseName", courseName).whereLessThan("courseName", courseName+'z').get());

            }
            allTasks = Tasks.whenAllSuccess(tasks);
        }
        else{
            task1 = firestore.collection("Courses").get();
            allTasks = Tasks.whenAllSuccess(task1);
        }

        allTasks.addOnSuccessListener(new OnSuccessListener<List<QuerySnapshot>>() {
            @Override
            public void onSuccess(List<QuerySnapshot> querySnapshots) {

                if(querySnapshots.stream().anyMatch(q->q.getDocuments().size()>0)) {
                    for (QuerySnapshot queryDocumentSnapshots : querySnapshots) {
                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                            CourseDto course = queryDocumentSnapshot.toObject(CourseDto.class);
                            addQueryCourse(course);
                            listener.onComplete();
                        }
                    }
                }
                else{
                    firestore.collection("Courses").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                                CourseDto course = queryDocumentSnapshot.toObject(CourseDto.class);
                                addQueryCourse(course);
                                listener.onComplete();
                            }
                        }
                    });
                }


            }
        });
    }
}
