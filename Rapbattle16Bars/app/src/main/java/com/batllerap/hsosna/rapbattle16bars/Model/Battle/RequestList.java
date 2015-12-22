package com.batllerap.hsosna.rapbattle16bars.Model.Battle;

import java.io.Serializable;

/**
 * Created by Dennis on 03.11.2015.
 */
public class RequestList implements Serializable{
    private Request[] requests;
    private Request[] opponent_requests;

    public RequestList(){

    }

    public RequestList(Request[] requests, Request[] opponentRequests){
        this.setRequests(requests);
        this.setOpponent_requests(opponentRequests);
    }

    public Request[] getRequests() {
        return requests;
    }

    public void setRequests(Request[] requests) {
        this.requests = requests;
    }

    public Request[] getOpponent_requests() {
        return opponent_requests;
    }

    public void setOpponent_requests(Request[] opponent_requests) {
        this.opponent_requests = opponent_requests;
    }
}
