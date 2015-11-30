package com.batllerap.hsosna.rapbattle16bars.Model.Profile;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Dennis on 03.11.2015.
 */
public class Rapper implements Serializable {
    Map<Integer, Battle> finishedBattles;
    Map<Integer, Battle> openBattles;
    int wins;
    int looses;
    String userName;

    public int getWins(){
        return  this.wins;
    }

    public int getLooses(){
        return  this.looses;
    }

    public String getUserName(){
        return this.userName;
    }

    public int getWinRate(){
        return this.wins/this.looses;
    }

    public Rapper(String userName, int wins, int looses){
        this.userName = userName;
        this.wins = wins;
        this.looses = looses;
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
