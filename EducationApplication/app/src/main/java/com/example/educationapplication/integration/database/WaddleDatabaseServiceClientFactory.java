package com.example.educationapplication.integration.database;

import android.content.Context;

import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;

import java.util.List;

import dataObjects.LoginUserDto;
import dataObjects.StudentLoginDto;
import dataObjects.StudentUserDto;
import dataObjects.UserDto;
import dataObjects.UserDto;

public class WaddleDatabaseServiceClientFactory {
    public static WaddleDatabaseServiceClient createClient(WaddleDatabaseConfiguration config) {
        if (config.isMocked()) {
            List<LoginUserDto> mockedUsers = List.of(
                    new StudentLoginDto("12345","MatthewRichards", "u7499989@anu.edu.au", "password1"),
                    new StudentLoginDto("54321", "TestUser", "other@testuser.com", "password2"),
                    new StudentLoginDto("43242", "AdminAdmin", "admin@admin.au", "password3")
            );
            return new MockWaddleDatabaseServiceClient(mockedUsers);
        }
        return new FirebaseWaddleDatabaseServiceClient();
    }
}
