package com.batllerap.hsosna.rapbattle16bars.Controller;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by woors on 23.11.2015.
 */
public class DataAccessControler {
    public static byte[] getPicture(int pictureId){
        //TODO: Logik erstellen
        return null;
    }

    public static byte[] getVideo(int videoId){
        //TODO: Logik erstellen
        return null;
    }

    public static void setDeviceToken(String token) throws JSONException {
        //TODO: Logik erstellen
        JSONObject obj = new JSONObject();
        obj.put("token",token);
    }
}
