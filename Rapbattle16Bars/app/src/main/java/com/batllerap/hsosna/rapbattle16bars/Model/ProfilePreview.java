package com.batllerap.hsosna.rapbattle16bars.Model;

import java.io.Serializable;

/**
 * Created by Dennis on 04.11.2015.
 */
public class ProfilePreview implements Serializable{
    private int user_id;
    private String username;
    private String profile_picture;


    public ProfilePreview(int userId, String username, String profilePicture){
        this.setUser_id(userId);
        this.setUsername(username);
        this.setProfile_picture(profilePicture);
    }

    public ProfilePreview(){

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }
}
