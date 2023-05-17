package com.example.educationapplication.util.views.Fragment.observer.messagefactory;

public class MessageFactory {

    public static String generateMessage(String course, String type){
        String s = "";
        if(type.equals("MODIFIED")){
            s = "There has been a change in " + course + ". Check it out now!";
        }
        if(type.equals("ADDED")){
            s = "A new course has been added, " + course + ". Check it out now!";
        }
        if(type.equals("REMOVED")){
            s = course + " Has Been removed and will no longer be available.";
        }
        return s;
    }
}
