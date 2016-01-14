/*
package com.batllerap.hsosna.rapbattle16bars.Controller;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.batllerap.hsosna.rapbattle16bars.MainActivity;
import com.batllerap.hsosna.rapbattle16bars.MultipartEntity;
import com.batllerap.hsosna.rapbattle16bars.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ImgUploadController extends AsyncTask<Bitmap, Void, Void> {

    private static CookieManager cookieManager;

    protected Void doInBackground(Bitmap... bitmaps) {
        System.out.println("Loos!");
        if (bitmaps[0] == null) {
            System.out.println("Null :( !!!!!!!!!!!!!!");
            return null;
        }

        Bitmap bitmap = bitmaps[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream); // convert Bitmap to ByteArrayOutputStream
        InputStream in = new ByteArrayInputStream(stream.toByteArray()); // convert ByteArrayOutputStream to ByteArrayInputStream

        //DefaultHttpClient httpclient = new DefaultHttpClient();
        URL link = null;
        try {
            link = new URL("http://46.101.216.34/profile/picture");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) link.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(cookieManager == null) {
            cookieManager = new CookieManager();
            CookieHandler.setDefault(new CookieManager());
        }

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        try {
            connection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        try {
            HttpPost httppost = new HttpPost(
                    "http://46.101.216.34/profile/picture"); // server

            MultipartEntity reqEntity = new MultipartEntity();
            reqEntity.addPart("picture",
                    System.currentTimeMillis() + ".jpg", in);
            httppost.setEntity(reqEntity);

            System.out.println("request " + httppost.getRequestLine());
            HttpResponse response = null;
            try {
                response = connection..execute(httppost);
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (response != null)
                    System.out.println("response " + response.getStatusLine().toString());
            } finally {

            }
        } finally {

        }

        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("FEEEEEEERTIIIIIIIG!!!!!!!!!!!!!!");
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
    }
}*/
