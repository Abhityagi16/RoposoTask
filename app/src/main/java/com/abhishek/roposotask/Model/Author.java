package com.abhishek.roposotask.Model;

import java.io.Serializable;

/**
 * Created by abhishektyagi on 03/04/16.
 */
public class Author implements Serializable {
    String userName, about, id, imageUrl, profileUrl, handle;
    boolean isFollowing;
    long followers, following;

    public Author(String id, String userName, String about, String imageUrl, String profileUrl,
                  String handle, long followers, long following, boolean isFollowing) {
        this.id = id;
        this.userName = userName;
        this.about = about;
        this.imageUrl = imageUrl;
        this.profileUrl = profileUrl;
        this.handle = handle;
        this.followers = followers;
        this.following = following;
        this.isFollowing = isFollowing;
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

    public void toggleFollowing() {
        if(isFollowing) {
            followers--;
        }
        else {
            followers++;
        }
        isFollowing = !isFollowing;
    }

    public boolean isFollowing() {
        return isFollowing;
    }
}
