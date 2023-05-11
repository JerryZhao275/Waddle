package com.example.educationapplication.integration.database;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;
import java.util.Map;

import dataObjects.AdminUserDto;
import dataObjects.StudentUserDto;
import dataObjects.TeacherUserDto;
import dataObjects.UserDto;

public class UserTypeFactory {
    public static UserDto createUser(String type, DocumentSnapshot map){
        UserDto user;
        if(type.equals("ADMIN")){
            AdminUserDto user1 = map.toObject(AdminUserDto.class);
            user = user1;
        }
        else if(type.equals("TEACHER")){
            TeacherUserDto user1 = map.toObject(TeacherUserDto.class);
            user = user1;
        }
        else{
            StudentUserDto user1 = map.toObject(StudentUserDto.class);
            user = user1;
        }
        return user;
    }
}
