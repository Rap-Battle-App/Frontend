package com.batllerap.hsosna.rapbattle16bars.Model.Battle;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.phaseInfo.Phase2Info;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.phaseInfo.PhaseInfo;
import com.batllerap.hsosna.rapbattle16bars.Model.ProfilePreview;

import java.io.Serializable;

/**
 * Created by woors on 23.11.2015.
 */
public class OpenBattle implements Serializable{
    private int id;
    private ProfilePreview opponent;
    private int phase;
    private Phase2Info info;

    public OpenBattle(){

    }

    public OpenBattle(int id, int userId, String username, String profilePicture, int phase, Phase2Info info){
        this.setId(id);
        this.setPhase(phase);
        this.setOpponent(new ProfilePreview(userId, username, profilePicture));
        this.setInfo(info);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProfilePreview getOpponent() {
        return opponent;
    }

    public void setOpponent(ProfilePreview opponent) {
        this.opponent = opponent;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public Phase2Info getInfo() {
        return info;
    }

    public void setInfo(Phase2Info info) {
        this.info = info;
    }
}
