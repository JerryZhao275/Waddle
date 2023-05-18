package com.example.educationapplication.viewmodels;

import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;

import java.util.ArrayList;
import java.util.List;

import com.example.educationapplication.search.dataObjects.CommentDto;
import com.example.educationapplication.search.dataObjects.CustomOnCompleteListener;
import com.example.educationapplication.search.dataObjects.DiscussionDto;

public class CommentViewModel {
    private final WaddleDatabaseConfiguration config;
    private final WaddleDatabaseServiceClient databaseServiceClient;
    List<CommentDto> comments = new ArrayList<>();
    DiscussionDto discussion;
    public CommentViewModel(DiscussionDto discussion, CustomOnCompleteListener listener){
        config = ConfigurationManager.configInstance();
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
        this.discussion = discussion;
        syncComments(new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                comments = databaseServiceClient.getComments();
                listener.onComplete();
            }
        });
    }

    public void syncComments(CustomOnCompleteListener listener){
        databaseServiceClient.syncComments(discussion.getDiscussionID(), new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                listener.onComplete();
            }
        });
    }

    public void addComment(CommentDto comment, CustomOnCompleteListener listener){
        databaseServiceClient.addComment(comment, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                listener.onComplete();
            }
        });
    }

    public void setComments(List<CommentDto> comments){
        this.comments = comments;
    }
    public List<CommentDto> getComments(){
        return comments;
    }
}
