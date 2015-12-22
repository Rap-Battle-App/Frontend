package com.batllerap.hsosna.rapbattle16bars.Model.response;

import com.batllerap.hsosna.rapbattle16bars.Model.ProfilePreview;

import java.io.Serializable;

/**
 * Created by woors on 17.12.2015.
 */
public class RandomOpponentResponse implements Serializable {
    private ProfilePreview opponent;

    public RandomOpponentResponse(){

    }

    public ProfilePreview getOpponent() {
        return opponent;
    }

    public void setOpponent(ProfilePreview opponent) {
        this.opponent = opponent;
    }
}
