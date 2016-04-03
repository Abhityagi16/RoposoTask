package com.abhishek.roposotask.Model;

/**
 * Created by abhishektyagi on 03/04/16.
 */
public class Author {
    String userName, about, id, imageUrl, profileUrl, handle;
    long followers, following;

    public Author(String id, String userName, String about, String imageUrl, String profileUrl,
                  String handle, long followers, long following) {
        this.id = id;
        this.userName = userName;
        this.about = about;
        this.imageUrl = imageUrl;
        this.profileUrl = profileUrl;
        this.handle = handle;
        this.followers = followers;
        this.following = following;
    }

    public String getUserName() {
        return userName;
    }

    public String getAbout() {
        return about;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getHandle() {
        return handle;
    }

    public long getFollowers() {
        return followers;
    }

    public long getFollowing() {
        return following;
    }

    public void incrementFollower() {
        followers++;
    }

    public void incrementFollowing() {
        following++;
    }
}
