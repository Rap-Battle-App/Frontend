package com.batllerap.hsosna.rapbattle16bars.Model;

import java.io.Serializable;

/**
 * Created by woors on 17.12.2015.
 */
public class Profile implements Serializable{
    private int id;
    private String username;
    private String profile_picture;
    private String city;
    private String about_me;
    private BattleStatistics statistics;
    private boolean rapper;

    public Profile(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public BattleStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(BattleStatistics statistics) {
        this.statistics = statistics;
    }

    public boolean isRapper() {
        return rapper;
    }

    public void setRapper(boolean rapper) {
        this.rapper = rapper;
    }
}
