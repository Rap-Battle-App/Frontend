package com.batllerap.hsosna.rapbattle16bars.Model.Battle;

import com.batllerap.hsosna.rapbattle16bars.Model.ProfilePreview;

import java.io.Serializable;

/**
 * Created by Dennis on 03.11.2015.
 */
public class Request implements Serializable {
    int id;
    ProfilePreview opponent;
    Date date;

    public int getId(){return this.id;}

    public ProfilePreview getOpponent(){
        return this.opponent;
    }

    public Date getDate(){ return this.date; }

    public Request(int id, ProfilePreview opponent, Date date){
        this.id = id;
        this.date = date;
        this.opponent = opponent;
    }
}
