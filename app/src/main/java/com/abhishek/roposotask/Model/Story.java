package com.abhishek.roposotask.Model;

/**
 * Created by abhishektyagi on 03/04/16.
 */
public class Story {
    private String id, title, description, authorId, imageUrl, verb;
    private long likesCount, commentCount;

    public Story(String id, String title, String authorId, String description, String imageUrl,
                 String verb, long
            likesCount, long
            commentCount) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.imageUrl = imageUrl;
        this.likesCount = likesCount;
        this.commentCount = commentCount;
        this.description = description;
        this.verb = verb;
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

    public String getVerb() {
        return verb;
    }

    public String getAuthorId() {
        return authorId;
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
