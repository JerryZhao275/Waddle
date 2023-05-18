package com.example.educationapplication.search;

/**
 * Course Description Expression for parsing
 */
public class CourseDescExpression implements Exp{
    String titleToken;
    Exp next;
    public CourseDescExpression(String titleToken, Exp next){
        this.titleToken = titleToken;
        this.next = next;
    }
    @Override
    public String showExpType() {
        return "Course Description";
    }

    @Override
    public String getCurrentValue() {
        return titleToken;
    }

    @Override
    public Exp getNext() {
        return next;
    }
}
