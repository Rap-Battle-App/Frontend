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

    //TODO: Serveradresse einfügen
    private static String serverUrl = "http://46.101.216.34";


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
        return "not implementet";
    }
}
