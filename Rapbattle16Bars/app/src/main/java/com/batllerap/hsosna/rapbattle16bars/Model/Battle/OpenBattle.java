package com.batllerap.hsosna.rapbattle16bars.Model.Battle;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.PhaseInfo.PhaseInfo;
import com.batllerap.hsosna.rapbattle16bars.Model.Profile.ProfilePreview;

import java.io.Serializable;

/**
 * Created by woors on 23.11.2015.
 */
public class OpenBattle implements Serializable{
    private int id;
    private ProfilePreview opponent;
    private int phase;
    PhaseInfo info;

    public int getId(){
        return this.id;
    }

    public ProfilePreview getOpponent(){
        return this.opponent;
    }

    public int getPhase(){
        return this.phase;
    }

    public PhaseInfo getInfo(){
        return this.info;
    }

    public OpenBattle(int id, int userId, String username, String profilePicture, int phase, PhaseInfo info){
        this.id = id;
        this.phase = phase;
        this.opponent = new ProfilePreview(userId, username, profilePicture);
        this.info = info;
    }
}
