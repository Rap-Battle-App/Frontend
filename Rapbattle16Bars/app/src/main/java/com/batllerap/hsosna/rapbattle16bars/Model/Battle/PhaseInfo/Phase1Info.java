package com.batllerap.hsosna.rapbattle16bars.Model.Battle.phaseInfo;

/**
 * Created by woors on 23.11.2015.
 */
public class Phase1Info extends PhaseInfo {
    private String round1_url;

    public Phase1Info(){

    }

    public Phase1Info(String round1Url, int timeLeft){
        this.setRound1_url(round1Url);
        this.time_left = timeLeft;
    }

    public String getRound1_url() {
        return round1_url;
    }

    public void setRound1_url(String round1_url) {
        this.round1_url = round1_url;
    }

    public int getTime_left(){
        return this.time_left;
    }

    public void setTime_left(int time_left){
        this.time_left = time_left;
    }
}
