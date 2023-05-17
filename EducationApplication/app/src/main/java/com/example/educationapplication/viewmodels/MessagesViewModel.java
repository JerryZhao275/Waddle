package com.example.educationapplication.viewmodels;

import android.widget.ArrayAdapter;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.educationapplication.BR;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import dataObjects.CustomOnCompleteListener;
import dataObjects.MessageDto;
import dataObjects.StudentUserDto;
import dataObjects.TeacherUserDto;
import dataObjects.UserDto;

public class MessagesViewModel extends BaseObservable {

    UserDto user;

    ArrayAdapter adapter;

    public static List<String> messages;

    WaddleDatabaseConfiguration config;
    WaddleDatabaseServiceClient databaseServiceClient;

    public MessagesViewModel() {
        user = new StudentUserDto();
        if(messages == null)
            messages = new ArrayList<>();
        messages.add("test");
        config = ConfigurationManager.configInstance();
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
        databaseServiceClient.fetchUserDetails(new CustomOnCompleteListener(){
            @Override
            public void onComplete() {
                user = databaseServiceClient.getUserDetails();
                System.out.println(user.getUserName()+" "+user.getUserEmail());
                for(MessageDto m: user.getDirectMessages()){
                    messages.add(m.getMessage());
                }
                if(adapter != null) adapter.notifyDataSetChanged();
            }
        });
        if(adapter != null) adapter.notifyDataSetChanged();
    }

    public List<String> getMessages(){
        return messages;
    }

    public void setAdapter(ArrayAdapter t){
        adapter = t;
    }
}
