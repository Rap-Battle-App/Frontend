package com.batllerap.hsosna.rapbattle16bars.Controller;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by woors on 09.12.2015.
 */
public class ConnectionController {
    /**
     * Sends a JSON Object to the server
     * @param url the url
     * @param obj the JSON Object
     * @param disconnect true if the connection should close after sending
     * @return returns true if successfull
     * @throws MalformedURLException
     * @throws IOException
     */
    public static boolean sendJSON(String url, JSONObject obj, boolean disconnect) throws MalformedURLException, IOException {
        URL link = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) link.openConnection();

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("POST");

        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
        wr.write(obj.toString());
        wr.flush();

        int response = connection.getResponseCode();
        if (response == HttpURLConnection.HTTP_OK) {
            connection.disconnect();
            return true;
        }
        System.out.println("Fehler beim Senden: " + connection.getResponseMessage());
        if(connection != null) {
            connection.disconnect();
        }
        return false;
    }

    /**
     * Requests a JSON Object from the Server
     * @param url the URL
     * @param requestJSON an Request JSON, if the Server doesnt need a Request, else null
     * @return true if succesfull
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getJSON(String url, JSONObject requestJSON) throws MalformedURLException, IOException {
        if(requestJSON != null){
            sendJSON(url,requestJSON,false);
        }

        int timeout = 20;

        HttpURLConnection c = null;
        URL u = new URL(url);
        c = (HttpURLConnection) u.openConnection();
        c.setRequestMethod("GET");
        c.setRequestProperty("Content-length", "0");
        c.setUseCaches(false);
        c.setAllowUserInteraction(false);
        c.setConnectTimeout(timeout);
        c.setReadTimeout(timeout);
        c.connect();
        int status = c.getResponseCode();

        switch (status) {
            case 200:
            case 201:
                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                return sb.toString();
        }

        if (c != null){
            c.disconnect();
        }
        return null;
    }
}
