package com.abhishek.roposotask.Model;

/**
 * Created by abhishektyagi on 03/04/16.
 */
public class Story {
    private String id, title, description, author, imageUrl, verb;
    private long likesCount, commentCount;

    public Story(String id, String title, String author, String imageUrl, String verb, long
            likesCount, long
            commentCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.imageUrl = imageUrl;
        this.likesCount = likesCount;
        this.commentCount = commentCount;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public long getLikesCount() {
        return likesCount;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void incrementCommentCount() {
        this.commentCount++;
    }

    public void incrementLikesCount() {
        this.likesCount++;
    }
}
