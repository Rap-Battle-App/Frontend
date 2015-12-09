package com.batllerap.hsosna.rapbattle16bars.Model.Battle;

import java.io.Serializable;

/**
 * Created by Dennis on 03.11.2015.
 */
public class RequestList implements Serializable{
    Request[] requests;
    Request[] opponentRequests;

    public Request[] getRequests(){
        return this.requests;
    }

    public Request[] getOpponentRequests(){
        return this.opponentRequests;
    }

    public RequestList(Request[] requests, Request[] opponentRequests){
        this.requests = requests;
        this.opponentRequests = opponentRequests;
    }
}
