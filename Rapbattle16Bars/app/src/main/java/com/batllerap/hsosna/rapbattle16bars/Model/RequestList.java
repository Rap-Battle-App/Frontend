package com.batllerap.hsosna.rapbattle16bars.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dennis on 03.11.2015.
 */
public class RequestList implements Serializable{
    List<Request> requests;
    List<Request> opponentRequests;

    public List<Request> getRequests(){
        return this.requests;
    }

    public List<Request> getOpponentRequests(){
        return this.opponentRequests;
    }

    public RequestList(List<Request> requests, List<Request> opponentRequests){
        this.requests = requests;
        this.opponentRequests = opponentRequests;
    }
}
