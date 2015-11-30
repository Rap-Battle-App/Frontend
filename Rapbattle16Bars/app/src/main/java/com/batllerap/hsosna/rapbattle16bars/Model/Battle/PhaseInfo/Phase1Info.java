package com.batllerap.hsosna.rapbattle16bars.Model.Battle.PhaseInfo;

/**
 * Created by woors on 23.11.2015.
 */
public class Phase1Info extends PhaseInfo {
    String round1Url;

    public String getRound1Url(){
        return this.round1Url;
    }

    public Phase1Info(String round1Url, int timeLeft){
        this.round1Url = round1Url;
        this.timeLeft = timeLeft;
    }
}
