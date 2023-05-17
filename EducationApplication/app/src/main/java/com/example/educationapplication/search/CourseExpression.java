package com.example.educationapplication.search;

public class CourseExpression implements Exp{
    String courseId;
    Exp courseDesc;
    public CourseExpression(String courseId, Exp courseDesc){
        this.courseId = courseId;
        this.courseDesc = courseDesc;
    }
    @Override
    public String showExpType() {
        return "COURSE ID";
    }

    @Override
    public String getCurrentValue() {
        return courseId;
    }

    @Override
    public Exp getNext() {
        return courseDesc;
    }
}
