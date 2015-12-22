package com.batllerap.hsosna.rapbattle16bars.Model;

import java.io.Serializable;

/**
 * Created by woors on 17.12.2015.
 */
public class BattleOverview implements Serializable{
    private int battle_id;
    private ProfilePreview rapper1;
    private ProfilePreview rapper2;

    public BattleOverview(){

    }

    public int getBattle_id() {
        return battle_id;
    }

    public void setBattle_id(int battle_id) {
        this.battle_id = battle_id;
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
}
