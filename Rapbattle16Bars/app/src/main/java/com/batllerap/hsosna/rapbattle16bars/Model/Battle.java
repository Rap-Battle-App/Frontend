package com.batllerap.hsosna.rapbattle16bars.Model;
 /**
 * Created by Dennis on 03.11.2015.
 */
public class Battle {
     private String rapper1Name;
     private String rapper2Name;
     private Beat beat1;
     private Beat beat2;
     private boolean isOpen;
     private int votes1;
     private int votes2;
     private int id;
     private String videoUrl;

     public String getRapper1(){
         return  this.rapper1Name;
     }

     public String getRapper2(){
         return  this.rapper2Name;
     }

    public Beat getBeat1(){
        return this.beat1;
    }

     public Beat getBeat2(){
         return this.beat2;
     }

     public int getVotes2(){
         return this.votes2;
     }

    public int getVotes1(){
        return this.votes1;
    }

    public boolean getisOpen(){
        return this.isOpen;
    }

     public void closeVoting(){
         this.isOpen = false;
     }

    public int getId(){
        return this.id;
    }

     public String getVideoUrl(){
         return this.videoUrl;
     }

    public Battle(int id, boolean isOpen, int votes1, int votes2, String rapper1Name, String rapper2Name, Beat beat1, Beat beat2, String videoUrl){
        this.id = id;
        this.isOpen = isOpen;
        this.votes1 = votes1;
        this.votes2 = votes2;
        this.rapper1Name = rapper1Name;
        this.rapper2Name = rapper2Name;
        this.beat1 = beat1;
        this.beat2 = beat2;
        this.videoUrl = videoUrl;
    }
}
