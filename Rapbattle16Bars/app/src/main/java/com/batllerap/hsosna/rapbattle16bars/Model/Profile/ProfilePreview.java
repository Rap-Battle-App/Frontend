package com.batllerap.hsosna.rapbattle16bars.Model.Profile;

import java.io.Serializable;

/**
 * Created by Dennis on 04.11.2015.
 */
public class ProfilePreview implements Serializable{
    int userId;
    String username;
    String profilePicture;

    public int getUserId(){
        return this.userId;
    }

    public String getUsername(){
        return this.username;
    }

    public String getProfilePicture(){
        return this.profilePicture;
    }

    public ProfilePreview(int userId, String username, String profilePicture){
        this.userId = userId;
        this.username = username;
        this.profilePicture = profilePicture;
    }
}
