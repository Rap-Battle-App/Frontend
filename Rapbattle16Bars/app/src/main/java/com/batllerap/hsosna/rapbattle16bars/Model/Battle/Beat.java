package com.batllerap.hsosna.rapbattle16bars.Model.Battle;

import java.io.Serializable;

/**
 * Created by Dennis on 03.11.2015.
 */
public class Beat implements Serializable{
    String name;
    int id;
    //TODO: BeatDatentyp beat

    public Beat(String name, int id/*, TODO: BeatDatentyp beat*/){
        this.id = id;
        this.name = name;
        //TODO: this.beat = beat;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
}
