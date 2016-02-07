package com.batllerap.hsosna.rapbattle16bars.Model.request;

import org.apache.http.protocol.HttpContext;

import java.io.File;
import java.io.Serializable;
import java.net.CookieManager;

/**
 * Created by woors on 19.01.2016.
 */
public class VideoUploadRequest implements Serializable {
    private int beat_id;
    private int battle_id;
    private File video;
    private String fileFormat;
    private HttpContext context;
    private CookieManager manager;

    public VideoUploadRequest(){

    }

    public VideoUploadRequest(int beatId, int battleId, File video, String format, HttpContext context, CookieManager manager){
        this.beat_id = beatId;
        this.battle_id = battleId;
        this.video = video;
        this.fileFormat = format;
        this.context = context;
        this.setManager(manager);
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

    public HttpContext getContext() {
        return context;
    }

    public void setContext(HttpContext context) {
        this.context = context;
    }

    public CookieManager getManager() {
        return manager;
    }

    public void setManager(CookieManager manager) {
        this.manager = manager;
    }
}
