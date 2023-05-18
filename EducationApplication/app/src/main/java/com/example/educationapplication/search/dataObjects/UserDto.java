package com.example.educationapplication.search.dataObjects;

import com.example.educationapplication.util.views.Fragment.observer.Observer;
import com.example.educationapplication.util.views.Fragment.observer.messagefactory.MessageFactory;
import com.example.educationapplication.viewmodels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;


public abstract class UserDto implements Observer, Serializable {



    private String userId;
    private String userFirstName;
    private String userName;
    private String userLastName;
    private String userEmail;
    private String userDesc;

    private static List<MessageDto> directMessages;


    private List<String> courses;

    public UserDto(){
        userId = "";
        userFirstName = "";
        this.userLastName = "";
        this.userName = "";
        this.userEmail = "";
        this.userDesc = "";
        this.courses = new ArrayList<>();
        if(directMessages == null){
            directMessages = new ArrayList<>();
        }

    }
    public UserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail,
                   String userDesc){
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userDesc = userDesc;
        if(directMessages == null)
            directMessages = new ArrayList<>();
    }

    public UserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail){
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userName = userName;
        this.userEmail = userEmail;
        if(directMessages == null)
            directMessages = new ArrayList<>();
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public String getUserId(){
        return userId;
    }

    public String getUserFirstName(){
        return userFirstName;
    }

    public String getUserLastName(){
        return userLastName;
    }

    public String getUserName(){
        return userName;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public String getUserDesc(){
        return userDesc;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setUserFirstName(String userFirstName){
        if(userFirstName!=null&&!userFirstName.equals("")){
            this.userFirstName = userFirstName;
        }
    }

    public void setUserLastName(String userLastName){
        if(userLastName!=null&&!userLastName.equals("")){
            this.userLastName = userLastName;
        }
    }

    public void setUserEmail(String userEmail){
        this.userEmail=userEmail;
    }

    public void setUserName(String userName){ this.userName = userName; }

    public void setUserDesc(String userDesc){
         this.userDesc = userDesc;
    }

    public void addMessage(String message, int courseID, int quizID){
        MessageDto m = new MessageDto(userId, courseID, quizID, message);
        directMessages.add(m);
    }

    public void setDirectMessages(List<MessageDto> messages){
        this.directMessages = messages;
    }

    public List<MessageDto> getDirectMessages(){
        return directMessages;
    }

    @Override
    public void update(String course, String type, String path, String name){
        System.out.println(getCourses());
        if(UserViewModel.getCoursesList() == null) return;
        if(UserViewModel.getCoursesList().contains(course))
            addMessage(MessageFactory.generateMessage(course, type, path, name), 0,0);
        System.out.println(directMessages.size());
    }

}
