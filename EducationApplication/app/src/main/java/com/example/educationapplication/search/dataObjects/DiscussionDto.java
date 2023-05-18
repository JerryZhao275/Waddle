package com.example.educationapplication.search.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * Discussion data objects
 */
public class DiscussionDto implements Serializable {
    // Discussion.java
    private String title;
    private String content;
    private String author;
    private Date timestamp;
    private String discussionID;
    private String courseID;
    private String authorID;

    public DiscussionDto(){
        this.title = "";
        this.content = "";
        this.author = "";
        this.timestamp = new Date();
        this.discussionID = "";
        this.courseID = "";
        this.authorID = "";
    }

    public DiscussionDto(String title, String content, String author, Date timestamp) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.timestamp = timestamp;
        this.discussionID = "";
        this.courseID = "";
        this.authorID = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthorID(){
        return authorID;
    }
    public void setAuthorID(String authorID){
        this.authorID = authorID;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public String getDiscussionID() {
        return discussionID;
    }
    public void setDiscussionID(String discussionID) {this.discussionID = discussionID;}

    public String getCourseID() {
        return courseID;
    }
    public void setCourseID(String courseID) {this.courseID = courseID;}

}
