package com.example.educationapplication.search.dataObjects;

import java.io.Serializable;
import java.util.List;

public class TeacherUserDto extends UserDto implements Serializable {
    private Integer age;
    private String school;
    public TeacherUserDto(){
        super();
        age = 0;
        school = "";
    }
    public TeacherUserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail, String userDesc, Integer age, String school) {
        super(userId, userFirstName, userLastName, userName, userEmail, userDesc);
        this.age = age;
        this.school = school;
    }

    public TeacherUserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail, String userDesc, Integer age) {
        super(userId, userFirstName, userLastName, userName, userEmail, userDesc);
        this.age = age;
    }

    public TeacherUserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail, Integer age) {
        super(userId, userFirstName, userLastName, userName, userEmail);
        this.age = age;
    }

    public TeacherUserDto(UserDto user){
        super(user.getUserId(), user.getUserFirstName(), user.getUserLastName(), user.getUserName(), user.getUserEmail());

    }

    public void setSchool(String school){
        this.school = school;
    }

    public String getSchool(){
        return this.school;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public Integer getAge(){
        return this.age;
    }

}
