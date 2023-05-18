package com.example.educationapplication.integration.database;

import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;

import java.util.List;

import com.example.educationapplication.search.dataObjects.LoginUserDto;

public class WaddleDatabaseServiceClientFactory {
    public static WaddleDatabaseServiceClient createClient(WaddleDatabaseConfiguration config) {
        if (config.isMocked()) {
            List<LoginUserDto> mockedUsers = List.of(
                    new LoginUserDto("12345","MatthewRichards", "u7499989@anu.edu.au", "password1"),
                    new LoginUserDto("54321", "TestUser", "other@testuser.com", "password2"),
                    new LoginUserDto("43242", "AdminAdmin", "admin@admin.au", "password3")
            );
            return new MockWaddleDatabaseServiceClient(mockedUsers);
        }
        return new FirebaseWaddleDatabaseServiceClient();
    }
}
