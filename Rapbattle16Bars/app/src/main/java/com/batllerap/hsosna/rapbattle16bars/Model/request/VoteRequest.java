package com.batllerap.hsosna.rapbattle16bars.Model.request;

import java.io.Serializable;

/**
 * Created by woors on 17.12.2015.
 */
public class VoteRequest implements Serializable {
    private int rapper_number;

    public VoteRequest(){

    }

    public int getRapper_number() {
        return rapper_number;
    }

    public void setRapper_number(int rapper_number) {
        this.rapper_number = rapper_number;
    }
}
