package com.batllerap.hsosna.rapbattle16bars.Controller;

import android.os.AsyncTask;

import com.batllerap.hsosna.rapbattle16bars.Model.AndroidMultiPartEntity;
import com.batllerap.hsosna.rapbattle16bars.Model.request.VideoUploadRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

/**
 * Created by woors on 07.02.2016.
 */
public class UploadVideoController extends AsyncTask<VideoUploadRequest, Integer, String> {
    long totalSize = 0;

    @Override
    protected String doInBackground(VideoUploadRequest... params) {
        VideoUploadRequest request = params[0];
        return uploadFile(request);
    }

    @SuppressWarnings("deprecation")
    private String uploadFile(VideoUploadRequest request) {
        String responseString = null;

        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.ACCEPT_ALL);
        HttpPost httppost = new HttpPost("http://46.101.216.34/open-battle/" + request.getBattle_id() + "/round");
        System.out.println("POST oder GET? " + httppost.getMethod());
        httppost.setHeader("Connection", "keep-alive");
        try {
            AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                    new AndroidMultiPartEntity.ProgressListener() {

                        @Override
                        public void transferred(long num) {
                            publishProgress((int) ((num / (float) totalSize) * 100));
                        }
                    });

            File sourceFile = request.getVideo();

            // Adding file data to http body
            entity.addPart("video", new FileBody(sourceFile));

            entity.addPart("beat_id", new StringBody("" + request.getBeat_id()));

            totalSize = entity.getContentLength();
            httppost.setEntity(entity);

            // Making server call
            HttpResponse response = httpclient.execute(httppost, request.getContext());
            HttpEntity r_entity = response.getEntity();

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Server response
                responseString = EntityUtils.toString(r_entity);
            } else {
                responseString = "Error occurred! Http Status Code: "
                        + statusCode;
            }

        } catch (ClientProtocolException e) {
            responseString = e.toString();
        } catch (IOException e) {
            responseString = e.toString();
        }

        return responseString;

    }

    @Override
    protected void onPostExecute(String result) {
        System.out.println("VideoUpload result: " + result);

    }
}