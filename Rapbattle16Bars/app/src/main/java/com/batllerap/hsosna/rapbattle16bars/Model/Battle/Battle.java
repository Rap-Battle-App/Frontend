package com.batllerap.hsosna.rapbattle16bars.Model.Battle;

import com.batllerap.hsosna.rapbattle16bars.Model.Profile.ProfilePreview;

import java.io.Serializable;

/**
 * Created by Dennis on 03.11.2015.
 */
public class Battle implements Serializable{
    private int id;
    private String videoUrl;
    private ProfilePreview rapper1;
    private ProfilePreview rapper2;
    private Voting voting;

    public ProfilePreview getRapper1(){
         return  this.rapper1;
     }

    public ProfilePreview getRapper2(){
         return  this.rapper2;
     }

    public Voting getVoting(){
        return this.voting;
    }

    public int getId(){
        return this.id;
    }

    public String getVideoUrl(){
         return this.videoUrl;
     }

    public Battle(int id, ProfilePreview rapper1, ProfilePreview rapper2, String videoUrl, Voting voting){
        this.id = id;
        this.rapper1 = rapper1;
        this.rapper2 = rapper2;
        this.voting = voting;
        this.videoUrl = videoUrl;
    }
}
