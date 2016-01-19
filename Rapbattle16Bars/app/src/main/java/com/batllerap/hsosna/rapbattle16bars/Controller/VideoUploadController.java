package com.batllerap.hsosna.rapbattle16bars.Controller;

import android.os.AsyncTask;

import com.batllerap.hsosna.rapbattle16bars.Model.request.VideoUploadRequest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
     * @param requests Ein VideoUploadRequest, in dem das Video, die BattleID, die BeatID und das Format vorher angegeben wird
     * @return
     */
    @Override
    protected Void doInBackground(VideoUploadRequest... requests){
       VideoUploadRequest request = requests[0];
        try {
            BattleController.uploadRound(request.getBattle_id(),request.getBeat_id(),request.getFileFormat(),request.getVideo());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

        /* URL url = null;
        try {
            url = new URL("http://46.101.216.34/open-battle/" + request.getBattle_id() + "/round");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        DataInputStream dis = null;
        FileInputStream fileInputStream = null;

        byte[] buffer;
        int maxBufferSize = 20 * 1024;
        try {
            // ------------------ CLIENT REQUEST
            fileInputStream = new FileInputStream(request.getVideo());

            // open a URL connection to the Servlet
            // Open a HTTP connection to the URL
            conn = (HttpURLConnection) url.openConnection();
            // Allow Inputs
            conn.setDoInput(true);
            // Allow Outputs
            conn.setDoOutput(true);
            // Don't use a cached copy.
            conn.setUseCaches(false);
            conn.setChunkedStreamingMode(1024);
            // Use a post method.
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);

            dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"video\"; filename=\"battle." + request.getFileFormat() + "\"" + lineEnd);
            dos.writeBytes("Content-Type:video/" + request.getFileFormat() + lineEnd);
            dos.writeBytes(lineEnd);

            // create a buffer of maximum size
            buffer = new byte[Math.min((int) request.getVideo().length(), maxBufferSize)];
            int length;
            // read file and write it into form...
            while ((length = fileInputStream.read(buffer)) != -1) {
                dos.write(buffer, 0, length);
            }

            // send multipart form data necessary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            dos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (dos != null)
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        // ------------------ read the SERVER RESPONSE
        try {
            dis = new DataInputStream(conn.getInputStream());
            StringBuilder response = new StringBuilder();

            String line;
            while ((line = dis.readLine()) != null) {
                response.append(line).append('\n');
            }

            System.out.println("Upload file responce:" + response.toString());
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dis != null)
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;*/
    }
}
