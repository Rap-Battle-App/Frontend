package com.batllerap.hsosna.rapbattle16bars.Model.request;

import java.io.Serializable;

/**
 * Created by woors on 17.12.2015.
 */
public class VoteRequest implements Serializable {
    private int rapper_numer;

    public VoteRequest(){

    }

    public int getRapper_numer() {
        return rapper_numer;
    }

    public void setRapper_numer(int rapper_numer) {
        this.rapper_numer = rapper_numer;
    }
}
