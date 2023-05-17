package dataObjects;

import java.util.Date;

public class CommentDto {
    private String comment;
    private String author;
    private Date timestamp;

    public CommentDto(String comment, String author, Date timestamp) {
        this.comment = comment;
        this.author = author;
        this.timestamp = timestamp;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
