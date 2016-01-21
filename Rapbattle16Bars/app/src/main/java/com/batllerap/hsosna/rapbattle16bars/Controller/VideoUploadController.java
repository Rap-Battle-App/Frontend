package com.batllerap.hsosna.rapbattle16bars.Controller;

import android.os.AsyncTask;

import com.batllerap.hsosna.rapbattle16bars.Model.request.VideoUploadRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by woors on 19.01.2016.
 */

public class VideoUploadController extends AsyncTask<VideoUploadRequest, Void, Void> {

    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary = "AaB03x87yxdkjnxvi7";

    /**
     * Versendet eine Battlerunde an den Server
     *
     * @param requests Ein VideoUploadRequest, in dem das Video, die BattleID, die BeatID und das Format vorher angegeben wird
     * @return
     */
    @Override
    protected Void doInBackground(VideoUploadRequest... requests) {
        VideoUploadRequest request = requests[0];
        /* try {
            BattleController.uploadRound(request.getBattle_id(),request.getBeat_id(),request.getFileFormat(),request.getVideo());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;*/

        URL url = null;
        try {
            url = new URL("http://46.101.216.34/open-battle/" + request.getBattle_id() + "/round");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://46.101.216.34/open-battle/" + request.getBattle_id() + "/round");
        FileBody body = new FileBody(request.getVideo());
        MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        reqEntity.addPart("video", body);
        try {
            reqEntity.addPart("beat_id", new StringBody("" + request.getBeat_id()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        post.setEntity(reqEntity);

        try {
            HttpResponse response = client.execute(post);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                String responseStr = EntityUtils.toString(resEntity).trim();
                System.out.println(responseStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
