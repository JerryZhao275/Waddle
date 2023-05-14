package dataObjects;

import java.util.Date;

public class DiscussionDto {
    // Discussion.java
    private String title;
    private String content;
    private String author;
    private Date timestamp;


    public DiscussionDto(String title, String content, String author, Date timestamp) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.timestamp = timestamp;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
