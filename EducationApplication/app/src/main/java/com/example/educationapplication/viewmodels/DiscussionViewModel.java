package com.example.educationapplication.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.educationapplication.BR;
import com.example.educationapplication.R;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;

import java.util.Date;

import dataObjects.CommentDto;
import dataObjects.CourseDto;

public class DiscussionViewModel extends BaseObservable {
    private String content;
    private String author;
    private Date timestamp = new Date();
    private final WaddleDatabaseConfiguration config;
    private final WaddleDatabaseServiceClient databaseServiceClient;

    public DiscussionViewModel(){
        config = ConfigurationManager.configInstance();
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
        author = "Current User";
        CommentDto comment = new CommentDto(content, author, timestamp);
    }

    @Bindable
    public String getDiscussionContent() {
        return content;
    }

    @Bindable
    public void setDiscussionContent(String content) {
        this.content = content;
        notifyPropertyChanged(BR.discussionContent);
    }



}
