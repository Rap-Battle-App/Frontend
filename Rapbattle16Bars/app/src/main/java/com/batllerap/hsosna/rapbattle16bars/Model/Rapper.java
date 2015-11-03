package com.batllerap.hsosna.rapbattle16bars.Model;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Dennis on 03.11.2015.
 */
public class Rapper {
    Map<Integer, Battle> finishedBattles;
    Map<Integer, Battle> openBattles;
    int wins;
    int looses;
    int rating;
    String userName;

    public int getWins(){
        return  this.wins;
    }

    public int getLooses(){
        return  this.looses;
    }

    public int getRating(){
        return  this.rating;
    }

    public int increaseRating(int value){   //Value kann auch negativ sein
        return this.rating += value;
    }

    public String getUserName(){
        return this.userName;
    }

    public int getWinRate(){
        return this.wins/this.looses;
    }

    public Rapper(String userName, int wins, int looses, int rating){
        this.userName = userName;
        this.wins = wins;
        this.looses = looses;
        this.rating = rating;
        this.finishedBattles = new HashMap<>();
        this.openBattles = new HashMap<>();
    }

    public void increaseWins(){
        this.wins++;
    }

    public void increaseLooses(){
        this.looses++;
    }

    public void addOpenBattle(Battle openBattle){
        this.openBattles.put(openBattle.getId(), openBattle);
    }

    public void addFinishedBattle(Battle finishedBattle){
        this.finishedBattles.put(finishedBattle.getId(), finishedBattle);
    }
}
