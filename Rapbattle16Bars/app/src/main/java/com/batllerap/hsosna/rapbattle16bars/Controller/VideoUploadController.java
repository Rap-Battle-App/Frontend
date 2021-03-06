package com.batllerap.hsosna.rapbattle16bars.Controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.batllerap.hsosna.rapbattle16bars.MainActivity;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
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
 * Created by Mark on 14.01.2016.
 */
public class VideoUploadController extends AsyncTask<File, Void, Void> {

    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary = "AaB03x87yxdkjnxvi7";
    String bounday2 = "125043040331425";
    Context context = null;
    String hyphens ="-----------------------------";
    VideoUploadRequest request;
    ProgressDialog pd = null;
    User aktUser = null;

    public VideoUploadController(Context context, VideoUploadRequest request, User aktUser){
        this.context = context;
        this.request = request;
        this.aktUser = aktUser;
    }
    public VideoUploadController( VideoUploadRequest request){

        this.request = request;
    }

    @Override
    protected void onPreExecute(){
        pd = ProgressDialog.show(context, "", "Video wird hochgeladen...", false);
    }

    protected Void doInBackground(File... files){
        System.out.println("Video UPLOAD");
        File file = files[0];
        URL url = null;
        try {
            url = new URL("http://46.101.216.34/open-battle/"+request.getBattle_id()+"/round");
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
            fileInputStream = new FileInputStream(file);

            // open a URL connection to the Servlet
            // Open a HTTP connection to the URL
            conn = (HttpURLConnection) url.openConnection();
            // Allow Inputs
            conn.setDoInput(true);
            // Allow Outputs
            conn.setDoOutput(true);
            // Don't use a cached copy.
            conn.setUseCaches(false);
            // Use a post method.
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            dos = new DataOutputStream( conn.getOutputStream() );

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"beat_id\"" + lineEnd + lineEnd);
            dos.writeBytes("" + request.getBeat_id()+"" + lineEnd);

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"video\";filename=\"video.mp4\"" + lineEnd);
            dos.writeBytes("Content-Type:video/mp4" + lineEnd);
            dos.writeBytes(lineEnd);

            // create a buffer of maximum size
            buffer = new byte[Math.min((int) file.length(), maxBufferSize)];
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
        conn.disconnect();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        // TODO Auto-generated method stub
        pd.dismiss();
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("Tab", 2);
        intent.putExtra("User", aktUser);
        context.startActivity(intent);
        super.onPostExecute(result);
    }
}
