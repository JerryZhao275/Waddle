package com.example.educationapplication.integration.database;

import com.example.educationapplication.search.dataObjects.MessageDto;
import com.google.firebase.firestore.DocumentSnapshot;

import com.example.educationapplication.search.dataObjects.AdminUserDto;
import com.example.educationapplication.search.dataObjects.StudentUserDto;
import com.example.educationapplication.search.dataObjects.TeacherUserDto;
import com.example.educationapplication.search.dataObjects.UserDto;

import java.util.List;

/**
 * User Factory to return the type of User: Admin, Teacher, Student
 */
public class UserTypeFactory {

    public static UserDto createUser(String type, DocumentSnapshot map){
        UserDto user = new StudentUserDto();
        List<MessageDto> messages = user.getDirectMessages();
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
        user.setDirectMessages(messages);
        return user;
    }
}
