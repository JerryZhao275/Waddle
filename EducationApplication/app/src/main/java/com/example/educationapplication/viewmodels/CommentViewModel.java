package com.example.educationapplication.viewmodels;

import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;
import java.util.ArrayList;
import java.util.List;
import com.example.educationapplication.search.dataObjects.*;

/**
 * View Model for the comments of a discussion
 */
public class CommentViewModel {
    private final WaddleDatabaseConfiguration config;
    private final WaddleDatabaseServiceClient databaseServiceClient;
    private List<CommentDto> comments = new ArrayList<>();
    private DiscussionDto discussion;

    /**
     * Constructor
     * @param discussion
     * @param listener
     */
    public CommentViewModel(DiscussionDto discussion, CustomOnCompleteListener listener) {
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

    /**
     * Synchronize the comments for the discussion from the database.
     *
     * @param listener The listener to be called when the synchronization is complete.
     */
    public void syncComments(CustomOnCompleteListener listener) {
        databaseServiceClient.syncComments(discussion.getDiscussionID(), new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                listener.onComplete();
            }
        });
    }

    /**
     * Add a new comment to the discussion.
     *
     * @param comment  The comment to be added.
     * @param listener The listener to be called when the comment is added.
     */
    public void addComment(CommentDto comment, CustomOnCompleteListener listener) {
        databaseServiceClient.addComment(comment, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                listener.onComplete();
            }
        });
    }

    /**
     * Set the list of comments.
     *
     * @param comments The list of comments to be set.
     */
    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    /**
     * Get the list of comments.
     *
     * @return The list of comments.
     */
    public List<CommentDto> getComments() {
        return comments;
    }
}
