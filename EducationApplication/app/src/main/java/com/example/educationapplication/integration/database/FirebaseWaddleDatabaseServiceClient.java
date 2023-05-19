package com.example.educationapplication.integration.database;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.educationapplication.search.dataObjects.AdminUserDto;
import com.example.educationapplication.search.dataObjects.CommentDto;
import com.example.educationapplication.search.dataObjects.CourseDto;
import com.example.educationapplication.search.dataObjects.CustomOnCompleteListener;
import com.example.educationapplication.search.dataObjects.DiscussionDto;
import com.example.educationapplication.search.dataObjects.LoginUserDto;
import com.example.educationapplication.search.dataObjects.TeacherUserDto;
import com.example.educationapplication.search.dataObjects.UserDto;
import com.example.educationapplication.search.dataObjects.UserType;
import com.example.educationapplication.util.views.Fragment.observer.Observer;
import com.example.educationapplication.util.views.Fragment.observer.Subject;
import com.example.educationapplication.model.CourseAVL;
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
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FirebaseWaddleDatabaseServiceClient implements WaddleDatabaseServiceClient, Subject {
    private final CourseAVL courseAVL = new CourseAVL();
    final private List<String> hardCodedCourses = new ArrayList<>(Arrays.asList("COMP2100", "COMP6320", "COMP1110"));
    final private FirebaseDatabase database;
    final private FirebaseFirestore firestore;
    final private FirebaseAuth mAuth;
    private String userType;

    private static ListenerRegistration coursesRegistration, discussionRegistration;
    private List<CourseDto> courseList;
    private List<DiscussionDto> discussions;
    private List<CommentDto> comments;

    /**
     * Constructor for Firebase Service Client, Listening to Course changes and syncing notifications
     * Accordingly
     */
    public FirebaseWaddleDatabaseServiceClient() {
        observers = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        fetchUserDetails(() -> {
        });

        if(coursesRegistration != null) coursesRegistration.remove();
        if(discussionRegistration != null) discussionRegistration.remove();

        coursesRegistration = firestore.collection("Courses").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {

                for(DocumentChange dc :snapshot.getDocumentChanges()){
                    switch (dc.getType()) {
                        case ADDED:
                            notifyAllObservers((String) dc.getDocument().get("courseName"), dc.getType().toString(), "COURSES","");
                            if(userDetails != null)
                                System.out.println("ADDED " + userDetails.getDirectMessages().size());
                            break;
                        case MODIFIED:
                            notifyAllObservers((String) dc.getDocument().get("courseName"), dc.getType().toString(), "COURSES","");
                            if(userDetails != null){
                                System.out.println("MODIFIED " + userDetails.getDirectMessages().size());
                            }
                            break;
                        case REMOVED:
                            notifyAllObservers((String) dc.getDocument().get("courseName"), dc.getType().toString(), "COURSES","");
                            if(userDetails != null)
                                System.out.println("REMOVED " + userDetails.getDirectMessages().size());
                            break;
                    }
                }

            }
        });
        discussionRegistration = firestore.collection("Discussions").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException error) {
                for(DocumentChange dc :snapshot.getDocumentChanges()){
                    switch (dc.getType()) {
                        case ADDED:
                            notifyAllObservers((String) dc.getDocument().get("courseID"), dc.getType().toString(), "DISCUSSION",(String) dc.getDocument().get("title"));
                            if(userDetails != null)
                                System.out.println("ADDED " + userDetails.getDirectMessages().size());
                            break;
                        case MODIFIED:
                            notifyAllObservers((String) dc.getDocument().get("courseID"), dc.getType().toString(), "DISCUSSION",(String) dc.getDocument().get("title"));
                            if(userDetails != null){
                                System.out.println("MODIFIED " + userDetails.getDirectMessages().size());
                            }
                            break;
                        case REMOVED:
                            notifyAllObservers((String) dc.getDocument().get("courseID"), dc.getType().toString(), "DISCUSSION",(String) dc.getDocument().get("title"));
                            if(userDetails != null)
                                System.out.println("REMOVED " + userDetails.getDirectMessages().size());
                            break;
                    }
                }
            }
        });


        courseAVL.insert(new CourseDto(1110, "COMP1110", null, "Structured Programming"));
        courseAVL.insert(new CourseDto(2100, "COMP2100", null, "Software Construction"));
        courseAVL.insert(new CourseDto(6320, "COMP6320", null, "Artificial Intelligence"));
    }

    private LoginUserDto currentUser = new LoginUserDto("","");
    private UserDto userDetails;
    private UserDto otherUserDetails;

    private ArrayList<Observer> observers;

    /**
     * gets current user
     * @return LoginUserDto user
     */
    @Override
    public LoginUserDto getCurrentUser() {
        return currentUser;
    }

    /**
     * fetches current logged in User details
     * @param listener
     */
    @Override
    public void fetchUserDetails(CustomOnCompleteListener listener) {
            DocumentReference docRef;
        try {
            docRef = firestore.collection("Users").document(mAuth.getCurrentUser().getUid());
        } catch (NullPointerException e){
            return;
        }
        database.getReference("Users").child(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot document1 = task.getResult();
                userType = document1.getValue().toString();
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()) {

                            DocumentSnapshot document = task.getResult();
                            detachAll();
                            userDetails = UserTypeFactory.createUser(userType, document);
                            attach(userDetails);
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

    /**
     * fetches details of the user searched.
     * @param user
     * @param listener
     */
    @Override
    public void fetchOtherUserDetails(UserDto user, CustomOnCompleteListener listener) {
        DocumentReference docRef = firestore.collection("Users").document(user.getUserId());
        database.getReference("Users").child(user.getUserId()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot document1 = task.getResult();
                userType = document1.getValue().toString();
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            otherUserDetails = UserTypeFactory.createUser(userType, document);
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

    /**
     * set the logged in user.
     * @param currentUser
     */
    public void setCurrentUser(LoginUserDto currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Attach observer
     * @param observer
     */
    @Override
    public void attach(Observer observer) {
        if(!observers.contains(observer))
            observers.add(observer);
    }

    /**
     * detach observer
     * @param observer
     */
    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notify all the observers related to a course
     * @param courseName
     * @param type
     * @param path
     * @param name
     */
    @Override
    public void notifyAllObservers(String courseName, String type, String path, String name) {
        for(Observer obs : observers){
            obs.update(courseName, type, path, name);
        }
    }

    /**
     * detach all observers
     */
    @Override
    public void detachAll() {
        observers = new ArrayList<>();
    }

    /**
     * Signs in the user that is trying to login in
     * @param email
     * @param password
     * @param listener
     * @return
     */
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
                            setCurrentUser(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            setCurrentUser(null);
                        }
                        listener.onComplete();
                    }

                });
        return false;
    }

    /**
     * set current user to null;
     */
    @Override
    public void setNullUser() {
        setCurrentUser(null);
    }

    /**
     * Fetch all user courses
     * @param listener
     */
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


    /**
     * get User courses that have been fetched
     * @return
     */
    @Override
    public List<CourseDto> getUserCourses() {
        return courseList;
    }

    /**
     * Adds courses set by a teacher account
     * @param course
     * @param listener
     */
    @Override
    public void addCourse(CourseDto course, CustomOnCompleteListener listener) {
        firestore.collection("Courses").document(course.getCourseId().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(!task.getResult().exists()){
                    firestore.collection("Courses").document(course.getCourseId().toString()).set(course).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if(task.isSuccessful()){
                                courseAVL.insert(course);
                                firestore.collection("Users").document(course.getTeacher().getUserId()).update("courses", FieldValue.arrayUnion(course.getCourseName())).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        listener.onComplete();
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });

    }

    /**
     * Updates changes made to the user itself
     * @param listener
     */
    @Override
    public void synchUsers(CustomOnCompleteListener listener){
        database.getReference("Users").child(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot document1 = task.getResult();
                userType = document1.getValue().toString();
                firestore.collection("Users").whereEqualTo("userId", mAuth.getCurrentUser().getUid()).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        courseList = new ArrayList<>();
                        if(error!=null){
                        }
                        else{
                            assert value != null;
                            for(DocumentSnapshot documentSnapshot : value.getDocuments()){
                                userDetails = UserTypeFactory.createUser(userType, documentSnapshot);
                            }
                            listener.onComplete();
                        }
                    }
                });
            }
        });

    }

    /**
     * Adds a discussion forum
     * @param discussion
     * @param listener
     */
    @Override
    public void addDiscussion(DiscussionDto discussion, CustomOnCompleteListener listener) {
        firestore.collection("Discussions").add(discussion)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            listener.onComplete();
                        }
                    }
                });
    }

    /**
     * Adds a comment in a discussion Forum
     * @param comment
     * @param listener
     */
    @Override
    public void addComment(CommentDto comment, CustomOnCompleteListener listener) {
        firestore.collection("Comments").add(comment)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            listener.onComplete();
                        }
                    }
                });
    }

    /**
     * Links Student and the courses
     * @param course
     * @param listener
     */
    @Override
    public void addStudentToCourse(String course, CustomOnCompleteListener listener) {
        if(course.length()==8) {
            firestore.collection("Courses").document(course.substring(4)).update("allStudents", FieldValue.arrayUnion(mAuth.getCurrentUser().getUid())).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        firestore.collection("Users").document(mAuth.getCurrentUser().getUid()).update("courses", FieldValue.arrayUnion(course)).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                listener.onComplete();
                            }
                        });
                    }
                }
            });
        }
    }


    /**
     * Sync any changes made to the course
     * @param listener
     */
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
        firestore.collection("Courses").whereArrayContains("allStudents", mAuth.getCurrentUser().getUid()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if(error!=null){
                    System.out.println("Course Error");
                }
                else{
                    assert value != null;
                    for(DocumentSnapshot documentSnapshot : value.getDocuments()){
                        CourseDto queryCourse = documentSnapshot.toObject(CourseDto.class);
                        if(courseList.stream().noneMatch(u -> u.getCourseName().equals(queryCourse.getCourseName()))) {
                            courseList.add(queryCourse);
                        }
                    }
                    listener.onComplete();
                }
            }
        });
    }

    /**
     * Get searched user details
     * @return
     */
    @Override
    public UserDto getOtherUserDetails(){
        return otherUserDetails;
    }

    /**
     * Get all current logged in user details
     * @return
     */
    public UserDto getUserDetails(){
        return userDetails;
    }

    /**
     * Get current logged in user ID
     * @return
     */
    @Override
    public String getCurrentUserId() {
        return mAuth.getCurrentUser().getUid();
    }

    /**
     * Sign Out current user
     */
    @Override
    public void signOut() {
        mAuth.signOut();
    }

    @Override
    public LoginUserDto getUser(String email, String password) {
        return null;
    }

    /**
     * Signs up a new user
     * @param user
     * @param password
     * @param listener
     */
    @Override
    public void createNewUser(UserDto user, String password, CustomOnCompleteListener listener) {
        mAuth.createUserWithEmailAndPassword(user.getUserEmail(), password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    user.setUserId(currentUser.getUid());
                    UserType type = null;
                    if(user instanceof AdminUserDto) {
                        type = UserType.ADMIN;
                    }
                    else if(user instanceof TeacherUserDto){
                        type = UserType.TEACHER;
                    }
                    else{
                        type = UserType.STUDENT;

                    }
                    database.getReference("Users").child(currentUser.getUid()).setValue(type).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            firestore.collection("Users").document(currentUser.getUid()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    listener.onComplete();
                                }
                            });
                        }
                    });

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

    /**
     * Gets the result for search a query for Users
     * @return
     */
    public List<UserDto> getQueryUsers(){
        return queryUsers;
    }
    /**
     * Adds the result for search a query for Courses
     * @return
     */
    public void addQueryCourse(CourseDto course){
        if(queryCourses.stream().noneMatch(u -> u.getCourseName().equals(course.getCourseName()))){
            queryCourses.add(course);
        }
    }
    /**
     * Gets the result for search a query for Courses
     * @return
     */
    public List<CourseDto> getQueryCourses(){
        return queryCourses;
    }

    /**
     * Query all users from a search Query
     * @param expression
     * @param listener
     */
    public void fetchAllUsersForSearch(Exp expression, CustomOnCompleteListener listener){
        List<String> users = new ArrayList<>();
        List<String> emails = new ArrayList<>();
        queryUsers = new ArrayList<>();
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
                                            UserDto user = UserTypeFactory.createUser(userType, queryDocumentSnapshot);
                                            addQueryUser(user);
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
                                database.getReference("Users").child(id).get().addOnCompleteListener(
                                        new OnCompleteListener<DataSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                DataSnapshot document1 = task.getResult();
                                                userType = document1.getValue().toString();
                                                UserDto user = UserTypeFactory.createUser(userType, queryDocumentSnapshot);
                                                addQueryUser(user);
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

    /**
     * Query all courses from a user Query
     * @param expression
     * @param listener
     */
    @Override
    public void fetchAllCoursesForSearch(Exp expression, CustomOnCompleteListener listener) {
        List<String> courseNames = new ArrayList<>();
        List<String> courseDescriptions = new ArrayList<>();
        queryCourses = new ArrayList<>();
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

    /**
     * Signs in various instances, used for created valid data instance samples.
     * @param email
     * @param password
     */
    @Override
    public void signInDataInstances(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password);
    }

    /**
     * Sync all discussions in joined courses
     * @param courseId
     * @param listener
     */
    @Override
    public void syncDiscussions(String courseId, CustomOnCompleteListener listener) {
        firestore.collection("Discussions").whereEqualTo("courseID", courseId).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    System.out.println("Discussion Failed to Sync");
                }
                else{
                    discussions = new ArrayList<>();
                    if(value!=null){
                        for(DocumentSnapshot document:value.getDocuments()){
                            DiscussionDto discussion = document.toObject(DiscussionDto.class);
                            discussions.add(discussion);
                        }
                    }
                    listener.onComplete();
                }
            }
        });
    }

    /**
     * return discussions
     * @return discussions
     */
    @Override
    public List<DiscussionDto> getDiscussions() {
        return discussions;
    }

    /**
     * Sync all comments in a discussion of a course
     * @param discussionID
     * @param listener
     */
    @Override
    public void syncComments(String discussionID, CustomOnCompleteListener listener) {
        firestore.collection("Comments").whereEqualTo("discussionID", discussionID).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    System.out.println("Failed to sync comments");
                }
                else{
                    if(value!=null){
                        comments = new ArrayList<>();
                        for(DocumentSnapshot document:value.getDocuments()){
                            CommentDto comment = document.toObject(CommentDto.class);
                            comments.add(comment);
                        }
                        listener.onComplete();
                    }
                }
            }
        });
    }

    /**
     * Get all comments
     * @return comments
     */
    @Override
    public List<CommentDto> getComments() {
        return comments;
    }

    /**
     * Creates a new user for a sample data instance
     * @param user
     * @param password
     * @param listener
     */
    @Override
    public void createNewUserDataInstance(UserDto user, String password, CustomOnCompleteListener listener) {
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
                    Random random = new Random();
                    int number = random.nextInt(hardCodedCourses.size());
                    List<String> newList = new ArrayList<>();
                    newList.add(hardCodedCourses.get(number));
                    firestore.collection("Users").document(currentUser.getUid()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            firestore.collection("Courses").document(newList.get(0).substring(4)).update("allStudents", FieldValue.arrayUnion(user.getUserId())).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        firestore.collection("Users").document(user.getUserId()).update("courses", FieldValue.arrayUnion(newList.get(0))).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                listener.onComplete();
                                            }
                                        });
                                    }
                                }
                            });
                            listener.onComplete();
                        }
                    });

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                }
            }
        });
    }
}
