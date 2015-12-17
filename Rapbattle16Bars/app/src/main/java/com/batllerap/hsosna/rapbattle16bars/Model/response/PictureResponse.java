package com.batllerap.hsosna.rapbattle16bars.Model.response;

import java.io.Serializable;

/**
 * Created by woors on 17.12.2015.
 */
public class PictureResponse implements Serializable {
    private byte picture;

    public PictureResponse(){

    }

    public byte getPicture() {
        return picture;
    }

    public void setPicture(byte picture) {
        this.picture = picture;
    }
}
