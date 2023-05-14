package com.example.educationapplication.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import dataObjects.CourseDto;
import dataObjects.TeacherUserDto;

public class CreateClassViewModel extends BaseObservable {
    private CourseDto course;
    private String courseName;
    private String courseDescription;
    private TeacherUserDto teacher;
    public CreateClassViewModel(){
        course = new CourseDto();
    }
    public void setTeacher(TeacherUserDto teacher){
        this.teacher = teacher;
    }
    @Bindable
    public String getCourseName(){
        return courseName;
    }

    @Bindable
    public void setCourseName(String courseName){
        this.courseName = courseName;
        course.setCourseName(courseName);
    }

    @Bindable
    public String getCourseDescription(){
        return courseName;
    }

    @Bindable
    public void setCourseDescription(String courseDescription){
        this.courseDescription = courseDescription;
        course.setCourseDescription(courseDescription);
    }
}
