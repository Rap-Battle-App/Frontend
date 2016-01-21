package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.android.internal.http.multipart.MultipartEntity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by woors on 09.12.2015.
 */
public class ConnectionController {

    //TODO: Serveradresse einfügen
    private static String serverUrl = "http://46.101.216.34";
    private static CookieManager cookieManager;

    /**
     * Sends a JSON Object to the server
     * @param url the url
     * @param obj the JSON Object
     * @return returns true if successfull
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String postJSON(String url, String obj) throws MalformedURLException, IOException {
        URL link = new URL(serverUrl + url);

        HttpURLConnection connection = (HttpURLConnection) link.openConnection();
        if(cookieManager == null) {
            cookieManager = new CookieManager();
            CookieHandler.setDefault(new CookieManager());
        }

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("POST");

        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
        System.out.println("Send: url: " + link + " JSON: " + obj);
        wr.write(obj);
        wr.flush();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        }
        System.out.println("Fehler beim Senden: " + connection.getResponseMessage());
        connection.disconnect();
        return "Fehler";
    }

    /**
     * Sends binary Data to the Server
     * @param url the URL where to send the Data
     * @param fileFormat like png, jpg mp4
     * @param entity the File
     * @return
     * @throws IOException
     */
    public static String sendData(String url, String fileFormat, HttpEntity entity) throws IOException {
        URL link = new URL(serverUrl + url);


        HttpURLConnection connection = (HttpURLConnection) link.openConnection();
        if(cookieManager == null) {
            cookieManager = new CookieManager();
            CookieHandler.setDefault(new CookieManager());
        }
        System.setProperty("http.keepAlive", "false");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        System.out.println("RequestType: video/" + fileFormat);
        connection.setRequestProperty("Content-Type", "video/" + fileFormat);

        connection.setRequestProperty("Content-Type", "multipart/form-data");
        connection.setRequestProperty("Connection", "close");
        connection.setRequestMethod("POST");
        connection.setChunkedStreamingMode(1024);

        connection.addRequestProperty("Content-length", entity.getContentLength() + "");
        connection.addRequestProperty(entity.getContentType().getName(), entity.getContentType().getValue());

        connection.connect();
        System.out.println(fileFormat + " wird gesendet");
        OutputStream os = connection.getOutputStream();
        entity.writeTo(connection.getOutputStream());
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("SendData "+ fileFormat + "ResponseCode: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(fileFormat + " Response: " + response.toString());
            return response.toString();
        }
        System.out.println("Fehler beim Senden: " + connection.getResponseMessage());
        connection.disconnect();
        return "Fehler";
    }

    /**
     * Requests a JSON Object from the Server
     * @param url the URL
     * @return the Response in JSON format
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getJSON(String url) throws MalformedURLException, IOException {
        URL link = new URL(serverUrl + url);
        HttpURLConnection con = (HttpURLConnection) link.openConnection();

        if(cookieManager == null) {
            cookieManager = new CookieManager();
            CookieHandler.setDefault(new CookieManager());
        }

        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + link);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println("GET Response: " + response.toString());
        con.disconnect();
        return response.toString();
    }
}
