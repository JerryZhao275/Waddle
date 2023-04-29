package com.example.educationapplication.integration.database;

import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;

import java.util.List;

import dataObjects.Student;
import dataObjects.User;

public class WaddleDatabaseServiceClientFactory {
    public static WaddleDatabaseServiceClient createClient(WaddleDatabaseConfiguration config) {
        if (true || config.isMocked()) {
            List<User> mockedUsers = List.of(
                    new Student(12345).withEmail("u7499989@anu.edu.au").withName("Matthew", "Richards"),
                    new Student(54321).withEmail("other@testuser.com").withName("Test", "User")
            );
            return new MockWaddleDatabaseServiceClient(mockedUsers);
        }
        return null;
    }
}
