package com.batllerap.hsosna.rapbattle16bars.Model.PhaseInfo;

/**
 * Created by woors on 23.11.2015.
 */
public class Phase2Info extends PhaseInfo{
    String round1Url;
    int beatId;
    String opponentRoundUrl;
    String round2Url;

    public String getRound1Url(){
        return this.round1Url;
    }

    public int getBeatId(){
        return this.beatId;
    }

    public String getOpponentRoundUrl(){
        return this.opponentRoundUrl;
    }

    public String getRound2Url(){
        return this.round2Url;
    }

    public Phase2Info(int timeLeft, String round1Url, int beatId, String opponentRoundUrl, String round2Url){
        super.timeLeft = timeLeft;
        this.round1Url = round1Url;
        this.beatId = beatId;
        this.opponentRoundUrl = opponentRoundUrl;
        this.round2Url = round2Url;
    }
}
