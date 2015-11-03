package com.batllerap.hsosna.rapbattle16bars.Model;

import java.util.Date;

/**
 * Created by Dennis on 03.11.2015.
 */
public class Request {
    Rapper requester;
    Rapper opponent;
    Date date;

    public Rapper getRequester(){
        return this.requester;
    }

    public Rapper getOpponent(){
        return this.opponent;
    }

    public Request(Rapper requester, Rapper opponent){
        date = new Date();
        this.requester = requester;
        this.opponent = opponent;
    }
}
