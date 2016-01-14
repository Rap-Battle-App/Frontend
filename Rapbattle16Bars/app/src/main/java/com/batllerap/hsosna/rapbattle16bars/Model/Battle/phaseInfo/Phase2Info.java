package com.batllerap.hsosna.rapbattle16bars.Model.Battle.phaseInfo;

/**
 * Created by woors on 23.11.2015.
 */
public class Phase2Info extends PhaseInfo{
    private String round1_url;
    private int beat_id;
    private String opponent_round1_rl;
    private String round2_url;

    public Phase2Info(){

    }

    public Phase2Info(int timeLeft, String round1Url, int beatId, String opponentRoundUrl, String round2Url){
        super.setTime_left(timeLeft);
        this.setRound1_url(round1Url);
        this.setBeat_id(beatId);
        this.setOpponent_round1_rl(opponentRoundUrl);
        this.setRound2_url(round2Url);
    }

    public String getRound1_url() {
        return round1_url;
    }

    public void setRound1_url(String round1_url) {
        this.round1_url = round1_url;
    }

    public int getBeat_id() {
        return beat_id;
    }

    public void setBeat_id(int beat_id) {
        this.beat_id = beat_id;
    }

    public String getOpponent_round1_rl() {
        return opponent_round1_rl;
    }

    public void setOpponent_round1_rl(String opponent_round1_rl) {
        this.opponent_round1_rl = opponent_round1_rl;
    }

    public String getRound2_url() {
        return round2_url;
    }

    public void setRound2_url(String round2_url) {
        this.round2_url = round2_url;
    }
}
