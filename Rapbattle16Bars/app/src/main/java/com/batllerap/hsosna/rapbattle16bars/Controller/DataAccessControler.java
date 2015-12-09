package com.batllerap.hsosna.rapbattle16bars.Controller;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by woors on 23.11.2015.
 */

public class DataAccessControler {

    public static byte getPicture(int pictureId) throws JSONException, IOException {
        String url = "/picture/" + pictureId;
        JSONObject response = new JSONObject(ConnectionController.getJSON(url, null));
        byte picture = (byte)response.getInt("picture");
        return picture;
    }

    public static byte getVideo(int videoId) throws JSONException, IOException{
        String url = "/video/" + videoId;
        JSONObject response = new JSONObject(ConnectionController.getJSON(url,null));
        byte video = (byte)response.getInt("video");
        return video;
    }

    public static void setDeviceToken(String token) throws JSONException, IOException {
        String url = "/device-token";
        JSONObject obj = new JSONObject();
        obj.put("token",token);
        ConnectionController.sendJSON(url,obj);
    }
}
