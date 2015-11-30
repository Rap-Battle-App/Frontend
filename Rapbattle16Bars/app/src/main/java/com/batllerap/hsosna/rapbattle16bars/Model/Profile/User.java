package com.batllerap.hsosna.rapbattle16bars.Model.Profile;

import java.io.Serializable;

/**
 * Created by Dennis on 03.11.2015.
 */
public class User implements Serializable {
    int id;
    String userName;
    String location;
    String aboutMe;
    String profilePicture;
    boolean isRapper;
    boolean notifications;
    Rapper rapper;

    public int getId(){
        return this.id;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getLocation(){
        return this.location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getAboutMe(){
        return this.aboutMe;
    }

    public void setAboutMe(String aboutMe){
        this.aboutMe = aboutMe;
    }

    public String getProfilePicture(){
        return this.profilePicture;
    }

    public boolean getIsRapper(){
        return this.isRapper;
    }

    public void setIsRapper(boolean isRapper){
        this.isRapper = isRapper;
    }

    public boolean getNotifications(){
        return this.notifications;
    }

    public void setNotifications(boolean notifications){
        this.notifications = notifications;
    }

    public Rapper getRapper(){
        return this.rapper;
    }

    public User(int id, String userName, String location, String aboutMe, String profilePicture, boolean isRapper, boolean notifications, Rapper rapper){
        this.userName = userName;
        this.location = location;
        this.aboutMe = aboutMe;
        this.profilePicture = profilePicture;
        this.isRapper = isRapper;
        this.notifications = notifications;
        this.rapper = rapper;
    }
}
