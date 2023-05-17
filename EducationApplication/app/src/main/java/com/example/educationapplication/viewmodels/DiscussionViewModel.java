package com.example.educationapplication.viewmodels;

import androidx.databinding.Bindable;

import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataObjects.CourseDto;
import dataObjects.CustomOnCompleteListener;
import dataObjects.DiscussionDto;
import dataObjects.UserDto;

public class DiscussionViewModel {
    private String content;
    private String author;
    private String authorId;
    private String courseId;
    private Date timestamp = new Date();
    private UserDto userDetails;
    List<DiscussionDto> discussions = new ArrayList<>();
    private final WaddleDatabaseConfiguration config;
    private final WaddleDatabaseServiceClient databaseServiceClient;

    public DiscussionViewModel(CourseDto course, CustomOnCompleteListener listener){
        courseId = course.getCourseName();
        config = ConfigurationManager.configInstance();
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
        databaseServiceClient.fetchUserDetails(new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                UserDto user = databaseServiceClient.getUserDetails();
                author = user.getUserFirstName()+" "+user.getUserLastName();
                authorId = user.getUserId();
                userDetails = user;
            }
        });
        syncDiscussions(listener);
    }

    public UserDto getUserDetails(){
        return userDetails;
    }

    public void syncDiscussions(CustomOnCompleteListener listener){
        databaseServiceClient.syncDiscussions(courseId, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                discussions = databaseServiceClient.getDiscussions();
                listener.onComplete();
            }
        });
    }

    public void addDiscussion(DiscussionDto discussion, CustomOnCompleteListener listener){
        discussion.setDiscussionID(courseId+"-"+(discussions.size()+1));
        discussion.setAuthor(author);
        discussion.setAuthorID(authorId);
        discussion.setCourseID(courseId);
        databaseServiceClient.addDiscussion(discussion, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                listener.onComplete();
            }
        });
    }

    public List<DiscussionDto> getDiscussions(){
        return discussions;
    }

    public String getDiscussionContent() {
        return content;
    }

    public void setDiscussionContent(String content) {
        this.content = content;
        //notifyPropertyChanged(BR.discussionContent);
    }



}
