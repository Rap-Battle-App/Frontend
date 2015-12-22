package com.batllerap.hsosna.rapbattle16bars.Model.Battle;

import com.batllerap.hsosna.rapbattle16bars.Model.ProfilePreview;

import java.io.Serializable;

/**
 * Created by Dennis on 03.11.2015.
 */
public class Battle implements Serializable{
    private int id;
    private String video_url;
    private ProfilePreview rapper1;
    private ProfilePreview rapper2;
    private Voting voting;

    public Battle(int id, ProfilePreview rapper1, ProfilePreview rapper2, String videoUrl, Voting voting){
        this.setId(id);
        this.setRapper1(rapper1);
        this.setRapper2(rapper2);
        this.setVoting(voting);
        this.setVideo_url(videoUrl);
    }

    public Battle(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public ProfilePreview getRapper1() {
        return rapper1;
    }

    public void setRapper1(ProfilePreview rapper1) {
        this.rapper1 = rapper1;
    }

    public ProfilePreview getRapper2() {
        return rapper2;
    }

    public void setRapper2(ProfilePreview rapper2) {
        this.rapper2 = rapper2;
    }

    public Voting getVoting() {
        return voting;
    }

    public void setVoting(Voting voting) {
        this.voting = voting;
    }
}
