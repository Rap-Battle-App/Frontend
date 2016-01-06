package com.batllerap.hsosna.rapbattle16bars.Model.request;

import java.io.Serializable;

/**
 * Created by woors on 17.12.2015.
 */
public class RoundRequest implements Serializable{
    private int beat_id;
    private byte[] video;

    public RoundRequest(){

    }

    public int getBeat_id() {
        return beat_id;
    }

    public void setBeat_id(int beat_id) {
        this.beat_id = beat_id;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo( byte[] video) {
        this.video = video;
    }
}
