package com.batllerap.hsosna.rapbattle16bars.Model.request;

import java.io.File;
import java.io.Serializable;

/**
 * Created by woors on 19.01.2016.
 */
public class VideoUploadRequest implements Serializable {
    private int beat_id;
    private int battle_id;
    private File video;
    private String fileFormat;

    public VideoUploadRequest(){

    }

    public VideoUploadRequest(int beatId, int battleId, File video, String format){
        this.beat_id = beatId;
        this.battle_id = battleId;
        this.video = video;
        this.fileFormat = format;
    }

    public int getBeat_id() {
        return beat_id;
    }

    public void setBeat_id(int beat_id) {
        this.beat_id = beat_id;
    }

    public int getBattle_id() {
        return battle_id;
    }

    public void setBattle_id(int battle_id) {
        this.battle_id = battle_id;
    }

    public File getVideo() {
        return video;
    }

    public void setVideo(File video) {
        this.video = video;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }
}
