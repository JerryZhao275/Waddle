package com.example.educationapplication.dataInstances;

import android.content.Context;

import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;
import com.example.educationapplication.util.CommonRegexUtil;
import com.example.educationapplication.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.regex.Pattern;

import dataObjects.CustomOnCompleteListener;
import dataObjects.StudentUserDto;

public class DataReader {

    private final WaddleDatabaseServiceClient databaseServiceClient;
    private final WaddleDatabaseConfiguration config;
    String password;

    public DataReader() {
        config = ConfigurationManager.configInstance();
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
    }

    public WaddleDatabaseServiceClient getDatabaseServiceClient() {
        return databaseServiceClient;
    }

    private String line;
    private BufferedReader loginBufferedReader;
    public void getUserInfo(Context context) {
        try {
            loginBufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open("testDataInstances.csv"), StandardCharsets.UTF_8));

            line = loginBufferedReader.readLine();
            dataLooper();

        } catch (IOException e) {
            System.out.println("Did not open csv file");
            e.printStackTrace();
        }
    }

    public void dataLooper() {
        if (line == null) {
            try{
                loginBufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        String[] tokens = line.split(",");
        if (tokens.length == 3) {
            String firstName = tokens[0];
            String lastName = tokens[1];
            String email = tokens[2];
            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println(email);
            createUserAndLogin(email, firstName, lastName, new CustomOnCompleteListener() {
                @Override
                public void onComplete() {
                    try {
                        line = loginBufferedReader.readLine();
                        dataLooper();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println(password);
        }
    }

    public void createUserAndLogin(String email, String firstName, String lastName, CustomOnCompleteListener listener) {
        boolean notEmpty = StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(firstName)
                && StringUtils.isNotEmpty(lastName);
        boolean isEmailValid = Pattern.matches(CommonRegexUtil.EMAIL, email);
        if (!notEmpty) {
            return;
        }
        if (!isEmailValid) {
            return;
        }
        String username = lastName + firstName;
        String password = generateRandomString(8);
        StudentUserDto newUser = new StudentUserDto("", firstName, lastName, username, email, "", 0);
        this.password = password;
        getDatabaseServiceClient().createNewUserDataInstance(newUser, password, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                listener.onComplete();
            }
        });

//        getDatabaseServiceClient().signIn(email, password, new CustomOnCompleteListener() {
//            @Override
//            public void onComplete() {
//                if (getDatabaseServiceClient().getCurrentUser() == null) {
//                    System.out.println("null user");
//                }
//                getDatabaseServiceClient().signOut();
//                getDatabaseServiceClient().setNullUser();
//            }
//        });
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
