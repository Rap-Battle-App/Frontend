package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.request.DeviceTokenRequest;
import com.batllerap.hsosna.rapbattle16bars.Model.response.PictureResponse;
import com.batllerap.hsosna.rapbattle16bars.Model.response.VideoResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by woors on 23.11.2015.
 */

public class dataAccessControler {

    public static byte getPicture(int pictureId) throws JSONException, IOException {
        String url = "/picture/" + pictureId;

        String responseString = ConnectionController.getJSON(url);
        System.out.println("getPictureResponse: " + responseString);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        PictureResponse response = gson.fromJson(responseString, PictureResponse.class);
        return response.getPicture();
    }

    public static byte getVideo(int videoId) throws JSONException, IOException{
        String url = "/video/" + videoId;

        String responseString = ConnectionController.getJSON(url);
        System.out.println("getPictureResponse: " + responseString);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        VideoResponse response = gson.fromJson(responseString, VideoResponse.class);
        return response.getVideo();
    }

    public static boolean setDeviceToken(String token) throws JSONException, IOException {
        String url = "/device-token";

        DeviceTokenRequest request = new DeviceTokenRequest();
        request.setToken(token);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestString = gson.toJson(request);

        String responseString = ConnectionController.postJSON(url, requestString);
        System.out.println("setDeviceToken response: " + responseString);

        return true;
    }
}
