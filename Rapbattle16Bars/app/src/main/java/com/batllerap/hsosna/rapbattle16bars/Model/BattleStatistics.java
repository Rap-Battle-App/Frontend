package com.batllerap.hsosna.rapbattle16bars.Model;

import java.io.Serializable;

/**
 * Created by woors on 17.12.2015.
 */
public class BattleStatistics implements Serializable{
    private int wins;
    private int defeats;

    public BattleStatistics(){

    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }
}
