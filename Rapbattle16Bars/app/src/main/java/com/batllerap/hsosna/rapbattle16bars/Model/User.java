package com.batllerap.hsosna.rapbattle16bars.Model;

/**
 * Created by Dennis on 03.11.2015.
 */
public class User {
    String userName;
    String realName;
    String location;
    String aboutMe;
    String profilePicture;
    boolean isRapper;
    boolean notifications;
    Rapper rapper;

    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getRealName(){
        return this.realName;
    }

    public String getLocation(){
        return this.location;
    }

    public String getAboutMe(){
        return this.aboutMe;
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

    public User(String userName, String realName, String location, String aboutMe, String profilePicture, boolean isRapper, boolean notifications, Rapper rapper){
        this.userName = userName;
        this.realName = realName;
        this.location = location;
        this.aboutMe = aboutMe;
        this.profilePicture = profilePicture;
        this.isRapper = isRapper;
        this.notifications = notifications;
        this.rapper = rapper;
    }
}
