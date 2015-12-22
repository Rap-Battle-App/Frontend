package com.batllerap.hsosna.rapbattle16bars.Model.profile2;

import java.io.Serializable;

/**
 * Created by Dennis on 03.11.2015.
 */
public class User implements Serializable {
    private int id;
    private String userName;
    private String location;
    private String aboutMe;
    private String profilePicture = "";
    private boolean isRapper;
    private boolean notifications;
    private Rapper rapper;

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

    public void setProfilePicture(String pic) { this.profilePicture = pic; }

    public void setIsRapper(boolean isRapper){
        this.isRapper = isRapper;
    }

    public boolean getNotifications(){
        return this.isNotifications();
    }

    public void setNotifications(boolean notifications){
        this.notifications = notifications;
    }

    public Rapper getRapper(){
        return this.rapper;
    }

    public User(int id, String userName, String location, String aboutMe, String profilePicture, boolean isRapper, boolean notifications, Rapper rapper){
        this.setUserName(userName);
        this.setLocation(location);
        this.setAboutMe(aboutMe);
        this.setProfilePicture(profilePicture);
        this.setIsRapper(isRapper);
        this.setNotifications(notifications);
        this.setRapper(rapper);
    }

    public User(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRapper() {
        return isRapper;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public void setRapper(Rapper rapper) {
        this.rapper = rapper;
    }
}
