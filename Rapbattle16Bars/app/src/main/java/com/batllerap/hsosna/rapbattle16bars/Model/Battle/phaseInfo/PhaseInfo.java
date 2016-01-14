package com.batllerap.hsosna.rapbattle16bars.Model.Battle.phaseInfo;

import java.io.Serializable;

/**
 * Created by woors on 23.11.2015.
 */
public class PhaseInfo implements Serializable {
    private int time_left;

    public PhaseInfo(){

    }

    public int getTime_left() {
        return time_left;
    }

    public void setTime_left(int time_left) {
        this.time_left = time_left;
    }
}
