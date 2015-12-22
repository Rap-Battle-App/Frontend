package com.batllerap.hsosna.rapbattle16bars.Model.request;

import java.io.Serializable;

/**
 * Created by woors on 17.12.2015.
 */
public class ProfilePictureRequest implements Serializable {
    private byte picture;

    public ProfilePictureRequest(){

    }

    public byte getPicture() {
        return picture;
    }

    public void setPicture(byte picture) {
        this.picture = picture;
    }
}
