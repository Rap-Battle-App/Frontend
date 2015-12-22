package com.batllerap.hsosna.rapbattle16bars.Model.response;

import java.io.Serializable;

/**
 * Created by woors on 17.12.2015.
 */
public class VideoResponse implements Serializable {
    private byte video;

    public VideoResponse(){

    }

    public byte getVideo() {
        return video;
    }

    public void setVideo(byte video) {
        this.video = video;
    }
}
