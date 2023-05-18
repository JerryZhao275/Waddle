package dataObjects;

import java.io.Serializable;
import java.util.Date;

public class CommentDto implements Serializable {
    private String comment;
    private String author;
    private Date timestamp;
    private String commentID;
    private String authorID;
    private String discussionID;
    public CommentDto(){
        this.comment = "";
        this.author = "";
        this.timestamp = new Date();
        commentID = "";
        discussionID = "";
        authorID = "";
    }

    public CommentDto(String comment, String author, Date timestamp) {
        this.comment = comment;
        this.author = author;
        this.timestamp = timestamp;
        commentID = "";
        discussionID = "";
        authorID = "";
    }

    public CommentDto(String commentID, String comment, Date timestamp, String author, String authorID, String discussionID) {
        this.comment = comment;
        this.author = author;
        this.timestamp = timestamp;
        this.commentID = commentID;
        this.discussionID = discussionID;
        this.authorID = authorID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getCommentID() {
        return commentID;
    }
    public void setCommentID(String comment) {
        this.comment = comment;
    }
    public String getDiscussionID() {
        return discussionID;
    }

    public void getDiscussionID(String discussionID) {
        this.discussionID = discussionID;
    }

}
