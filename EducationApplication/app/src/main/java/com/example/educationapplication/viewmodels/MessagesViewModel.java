package com.example.educationapplication.viewmodels;

import android.widget.ArrayAdapter;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import com.example.educationapplication.search.dataObjects.CustomOnCompleteListener;
import com.example.educationapplication.search.dataObjects.MessageDto;
import com.example.educationapplication.search.dataObjects.StudentUserDto;
import com.example.educationapplication.search.dataObjects.UserDto;

public class MessagesViewModel extends BaseObservable{

    @Bindable
    UserDto userData;

    ArrayAdapter adapter;

    public List<String> messages;

    WaddleDatabaseConfiguration config;
    WaddleDatabaseServiceClient databaseServiceClient;

    public MessagesViewModel() {
        userData = new StudentUserDto();
        if(messages == null)
            messages = new ArrayList<>();
        //messages.add("test");
        config = ConfigurationManager.configInstance();
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
        databaseServiceClient.fetchUserDetails(new CustomOnCompleteListener(){
            @Override
            public void onComplete() {
                userData = databaseServiceClient.getUserDetails();
                System.out.println(userData.getUserName()+" "+ userData.getUserEmail());
                for(MessageDto m: userData.getDirectMessages()){
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
