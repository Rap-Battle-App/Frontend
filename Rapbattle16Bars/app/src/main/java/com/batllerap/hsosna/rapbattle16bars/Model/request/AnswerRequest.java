package com.batllerap.hsosna.rapbattle16bars.Model.request;

import java.io.Serializable;

/**
 * Created by woors on 17.12.2015.
 */
public class AnswerRequest implements Serializable {
    private boolean accepted;

    public AnswerRequest(){

    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
