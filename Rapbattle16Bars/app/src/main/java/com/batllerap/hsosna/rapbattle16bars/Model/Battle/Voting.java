package com.batllerap.hsosna.rapbattle16bars.Model.Battle;

/**
 * Created by woors on 09.12.2015.
 */
public class Voting {
    private int votesRapper1;
    private int votesRapper2;
    private int votedFor;
    private boolean isOpen;

    public int getVotesRapper1() {
        return votesRapper1;
    }

    public void setVotesRapper1(int votesRapper1) {
        this.votesRapper1 = votesRapper1;
    }

    public int getVotesRapper2() {
        return votesRapper2;
    }

    public void setVotesRapper2(int votesRapper2) {
        this.votesRapper2 = votesRapper2;
    }

    public int getVotedFor() {
        return votedFor;
    }

    public void setVotedFor(int votedFor) {
        this.votedFor = votedFor;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Voting(int votesRapper1, int votesRapper2, int votedFor, boolean isOpen){
        this.votesRapper1 = votesRapper1;
        this.votesRapper2 = votesRapper2;
        this.votedFor = votedFor;
        this.isOpen = isOpen;
    }
}
