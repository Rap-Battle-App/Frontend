package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.OpenBattle;
import com.batllerap.hsosna.rapbattle16bars.Model.ProfilePreview;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.RequestList;
import com.batllerap.hsosna.rapbattle16bars.Model.request.AnswerRequest;
import com.batllerap.hsosna.rapbattle16bars.Model.request.RequestRequest;
import com.batllerap.hsosna.rapbattle16bars.Model.request.RoundRequest;
import com.batllerap.hsosna.rapbattle16bars.Model.request.VideoUploadRequest;
import com.batllerap.hsosna.rapbattle16bars.Model.request.VoteRequest;
import com.batllerap.hsosna.rapbattle16bars.Model.response.BattleListResponse;
import com.batllerap.hsosna.rapbattle16bars.Model.response.RandomOpponentResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Dennis on 03.11.2015.
 */
public class BattleController {

    private static String getRequestString(Serializable obj){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(obj);
    }

    /**
     * Returns a BattleList Array with trendingBattles
     * @param amount maximum of Battles in the BattleList
     * @param page the page of the list
     * @return
     */
    public static BattleListResponse getTrendingBattles(int page, int amount) throws IOException {
        String url = "/battles/trending?amount=" + amount + "&page=" + page;

        String responseString = ConnectionController.getJSON(url);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BattleListResponse response = gson.fromJson(responseString, BattleListResponse.class);

        return response;
    }

    /**
     * Returns a BattleList Array with Battles, wich are open for voting
     * @param userId The User, whos Battles are open for voting
     * @param page the page of the list
     * @param amount how many Battles
     * @return
     */
    public static BattleListResponse getOpenForVotingBattles(int userId, int page, int amount) throws IOException {
        String url = "/battles/open-voting?amount=" + amount + "&user_id=" + userId + "&page=" + page;

        String responseString = ConnectionController.getJSON(url);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BattleListResponse response = gson.fromJson(responseString, BattleListResponse.class);

        return response;
    }

    public static BattleListResponse getOpenForVotingBattles(int page, int amount) throws IOException {
        String url = "/battles/open-voting?amount=" + amount + "&page=" + page;

        String responseString = ConnectionController.getJSON(url);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BattleListResponse response = gson.fromJson(responseString, BattleListResponse.class);

        return response;
    }
/*
    public static BattleListResponse getOpenForVotingBattles(int page, int amount) throws IOException {
        String url = "/battles/open-voting";

        String responseString = ConnectionController.getJSON(url);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BattleListResponse response = gson.fromJson(responseString, BattleListResponse.class);

        return response;
    }
*/
    /**
     * Returns a BattleList Array with completed Battles
     * @param userId The User, who completed the Battles
     * @param page the page of the list
     * @param amount how many Battles
     * @return
     */
    public static BattleListResponse getCompletedBattles(int userId, int page, int amount) throws IOException {
        String url = "/battles/completed?amount=" + amount + "&user_id=" + userId + "&page=" + page;

        String responseString = ConnectionController.getJSON(url);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BattleListResponse response = gson.fromJson(responseString, BattleListResponse.class);

        return response;
    }

    /**
     * Returns a BattleList Array with open Battles
     * @param page
     * @return
     */
    public static BattleListResponse getOpenBattles(int page) throws IOException {
        String url = "/battles/open?page="+page;

        String responseString = ConnectionController.getJSON(url);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BattleListResponse response = gson.fromJson(responseString, BattleListResponse.class);

        return response;
    }


    /**
     * Returns a completed or open for Voting Battle
     * @param battleId if id == 0 returns a completed Battle, if id==1 returns a open for Voting battle else null
     * @return
     */
    public static Battle getBattle(int battleId) throws IOException {
        String url = "/battle/" + battleId;
        String responseString = ConnectionController.getJSON(url);
        System.out.println("GetBattle response: " + responseString);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        return gson.fromJson(responseString, Battle.class);
    }

    public static OpenBattle getOpenBattle(int battleId) throws IOException {
        String url = "/open-battle/" + battleId;

        String responseString = ConnectionController.getJSON(url);
        System.out.println("getOpenBattle response: " + responseString);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        return gson.fromJson(responseString, OpenBattle.class);
    }

    /**
     * Uploads a Video to the Server
     * @param battleId the Id of the Battle
     * @param beatId the Id of the chosen beat
     * @param fileFormat like mp4, wmv...
     * @param videoFile the Videofile
     * @throws IOException
     */
    public static void uploadRound(int battleId, int beatId, String fileFormat, File videoFile) throws IOException {
        String url = "/open-battle/" + battleId + "/round";
        FileInputStream stream;
        stream = new FileInputStream(videoFile);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        //builder.setContentType(ContentType.MULTIPART_FORM_DATA);
        builder.addBinaryBody("video", stream);
        builder.addTextBody("beat_id","" + beatId);
        HttpEntity entity = builder.build();

        //String responseString =  ConnectionController.sendData(url, fileFormat, entity);
       // System.out.println("UploadRound response: " + responseString);
    }

    public static String uploadRound2(int battleId, int beatId, String fileFormat, File videoFile) throws IOException{
        String urlServer = "/open-battle/" + battleId + "/round";
        String response = "error";
        HttpURLConnection connection = null;
        DataOutputStream outputStream = null;

        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024;
        try {
            FileInputStream fileInputStream = new FileInputStream(videoFile);

            URL url = new URL(urlServer);
            connection = (HttpURLConnection) url.openConnection();

            // Allow Inputs & Outputs
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setChunkedStreamingMode(1024);
            // Enable POST method
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);

            String connstr = null;
            connstr = "Content-Disposition: form-data; name=\"uploadedfile\";filename=\"video\"" + lineEnd;


            outputStream.writeBytes(connstr);
            outputStream.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            // Read file
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            System.out.println("File length" + bytesAvailable + "");
            try {
                while (bytesRead > 0) {
                    try {
                        outputStream.write(buffer, 0, bufferSize);
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                        response = "outofmemoryerror";
                        return response;
                    }
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }
            } catch (Exception e) {
                e.printStackTrace();
                response = "error";
                return response;
            }
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + twoHyphens
                    + lineEnd);

            // Responses from the server (code and message)
            int serverResponseCode = connection.getResponseCode();
            String serverResponseMessage = connection.getResponseMessage();
            System.out.println("Video Upload Server Response Code " + serverResponseCode);
            System.out.println("Video Upload Server Response Message " + serverResponseMessage);

            if (serverResponseCode == 200) {
                response = "true";
            }

            fileInputStream.close();
            outputStream.flush();
            outputStream.close();
            outputStream = null;
        } catch (Exception ex) {
            // Exception handling
            response = "error";
            System.out.println("Video Upload: Send file Exception" + ex.getMessage() + "");
            ex.printStackTrace();
        }
        return response;
    }

    public static void upload3(VideoUploadRequest request) throws FileNotFoundException {
        String urlServer = "http://46.101.216.34/open-battle/" + request.getBattle_id() + "/round";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("video", request.getVideo());
        params.put("beat_id", request.getBeat_id());
        ResponseHandlerInterface responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("Videoupload erfolgreich");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("Videoupload gescheitert, errorCode: " + statusCode);
            }
        };
        client.post(urlServer, params, responseHandler);
    }

    /**
     * Returns a RequestList of Requests for a Rapper
     * @param rapperName the name of the Rapper
     * @return
     */
    public static RequestList getRequestList(String rapperName) throws IOException {
        String url = "/requests";

        String responseString = ConnectionController.getJSON(url);
        System.out.println("getRequestList response: " + responseString);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(responseString,RequestList.class);
    }

    /**
     * Sends a Request to an random opponent
     */
    public static ProfilePreview sendRequest() throws IOException {
        String url = "/request/random";

        String responseString = ConnectionController.getJSON(url);
        System.out.println("sendRequest response: " + responseString);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        RandomOpponentResponse response = gson.fromJson(responseString, RandomOpponentResponse.class);

        return response.getOpponent();
    }

    /**
     * sends a Request to a Rapper
     * @param userId
     */
    public static void sendRequest(int userId) throws IOException {
        String url = "/request";
        RequestRequest request = new RequestRequest();
        request.setUser_id(userId);

        String requestString = getRequestString(request);

        String responseString = ConnectionController.postJSON(url, requestString);
        System.out.println(responseString);
    }

    /**
     * gives a vote for a Battle to the Database
     * @param battleId
     * @param rapperNumber the name of the rapper, who gets the Vote
     */
    public static void voteBattle(int battleId, int rapperNumber) throws IOException {
        String url = "/battle/" + battleId + "/vote";
        VoteRequest request = new VoteRequest();
        request.setRapper_number(rapperNumber);

        String requestString = getRequestString(request);

        String responseString = ConnectionController.postJSON(url, requestString);
        System.out.println("voteBattle response: " +responseString);
    }

    /**
     * answers a Request
     * @param id
     * @param accepted
     * @param accepted true if the request is accepted, else false
     */
    public static void answerRequest(int id, boolean accepted) throws IOException {
        String url = "/request/" + id;
        AnswerRequest request = new AnswerRequest();
        request.setAccepted(accepted);

        String requestString = getRequestString(request);

        String responseString = ConnectionController.postJSON(url, requestString);
        System.out.println("AnswerRequest response: " + responseString);
    }
}
