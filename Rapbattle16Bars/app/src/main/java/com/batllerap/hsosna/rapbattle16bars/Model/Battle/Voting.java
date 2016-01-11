package com.batllerap.hsosna.rapbattle16bars.Model.Battle;

import java.io.Serializable;

/**
 * Created by woors on 09.12.2015.
 */
public class Voting implements Serializable{
    private int votes_rapper1 = 0;
    private int votes_rapper2 = 0;
    private int voted_for = 0;
    private boolean isOpen;


    public Voting(int votesRapper1, int votesRapper2, int votedFor, boolean isOpen){
        this.setVotes_rapper1(votesRapper1);
        this.setVotes_rapper2(votesRapper2);
        this.setVoted_for(votedFor);
        this.setIsOpen(isOpen);
    }

    public int getVotes_rapper1() {
        return votes_rapper1;
    }

    public void setVotes_rapper1(int votes_rapper1) {
        this.votes_rapper1 = votes_rapper1;
    }

    public int getVotes_rapper2() {
        return votes_rapper2;
    }

    public void setVotes_rapper2(int votes_rapper2) {
        this.votes_rapper2 = votes_rapper2;
    }

    public int getVoted_for() {
        return voted_for;
    }

    public void setVoted_for(int voted_for) {
        this.voted_for = voted_for;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}
