package com.example.educationapplication.util.views.Fragment.observer.messagefactory;

public class MessageFactory {

    public static String generateMessage(String course, String type, String path, String name){
        String s = "";
        if(path.equals("COURSES")){
            if(type.equals("MODIFIED")){
                s = "There has been a change in " + course + ". Check it out now!";
            }
            if(type.equals("ADDED")){
                s = "A new course has been added, " + course + ". Check it out now!";
            }
            if(type.equals("REMOVED")){
                s = course + " Has Been removed and will no longer be available.";
            }
        }
        else if(path.equals("DISCUSSION")){
            if(type.equals("MODIFIED")){
                s = "The discussion titled, '" + name + "' in the course " + course + " has been modified. Check it out now!";
            }
            if(type.equals("ADDED")){
                s = "A new discussion titled, '" + name + "' in the course " + course + " has been added. Check it out now!";
            }
            if(type.equals("REMOVED")){
                s = "The discussion titled, '" + name + "' in the course " + course + " has been removed.";
            }
        }


        return s;
    }
}
