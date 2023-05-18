package com.example.educationapplication.viewmodels;

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

    public DiscussionViewModel(CourseDto course, CustomOnCompleteListener listener) {
        courseId = course.getCourseName();
        config = ConfigurationManager.configInstance();
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
        databaseServiceClient.fetchUserDetails(new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                UserDto user = databaseServiceClient.getUserDetails();
                author = user.getUserFirstName() + " " + user.getUserLastName();
                authorId = user.getUserId();
                userDetails = user;
            }
        });
        syncDiscussions(listener);
    }

    /**
     * Get the user details associated with the discussion.
     *
     * @return The user details.
     */
    public UserDto getUserDetails() {
        return userDetails;
    }

    /**
     * Synchronize the discussions for the course from the database.
     *
     * @param listener The listener to be called when the synchronization is complete.
     */
    public void syncDiscussions(CustomOnCompleteListener listener) {
        databaseServiceClient.syncDiscussions(courseId, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                discussions = databaseServiceClient.getDiscussions();
                listener.onComplete();
            }
        });
    }

    /**
     * Add a new discussion to the database.
     *
     * @param discussion The discussion to be added.
     * @param listener   The listener to be called when the addition is complete.
     */
    public void addDiscussion(DiscussionDto discussion, CustomOnCompleteListener listener) {
        discussion.setDiscussionID(courseId + "-" + (discussions.size() + 1));
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

    /**
     * Get the discussions associated with the course.
     *
     * @return The list of discussions.
     */
    public List<DiscussionDto> getDiscussions() {
        return discussions;
    }

    /**
     * Get the content of the discussion.
     *
     * @return The content of the discussion.
     */
    public String getDiscussionContent() {
        return content;
    }

    /**
     * Set the content of the discussion.
     *
     * @param content The content of the discussion.
     */
    public void setDiscussionContent(String content) {
        this.content = content;
        //notifyPropertyChanged(BR.discussionContent);
    }
}