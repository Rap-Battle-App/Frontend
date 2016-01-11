package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.OpenBattle;
import com.batllerap.hsosna.rapbattle16bars.Model.ProfilePreview;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.RequestList;
import com.batllerap.hsosna.rapbattle16bars.Model.request.AnswerRequest;
import com.batllerap.hsosna.rapbattle16bars.Model.request.RequestRequest;
import com.batllerap.hsosna.rapbattle16bars.Model.request.RoundRequest;
import com.batllerap.hsosna.rapbattle16bars.Model.request.VoteRequest;
import com.batllerap.hsosna.rapbattle16bars.Model.response.BattleListResponse;
import com.batllerap.hsosna.rapbattle16bars.Model.response.RandomOpponentResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

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

    public static BattleListResponse getOpenForVotingBattles(int page, int amount) throws IOException {
        String url = "/battles/open-voting";

        String responseString = ConnectionController.getJSON(url);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BattleListResponse response = gson.fromJson(responseString, BattleListResponse.class);

        return response;
    }

    /**
     * Returns a BattleList Array with completed Battles
     * @param userId The User, who completed the Battles
     * @param page the page of the list
     * @param amount how many Battles
     * @return
     */
    public static BattleListResponse getCompletedBattles(int userId, int page, int amount) throws IOException {
        String url = "/battles/open-voting?amount=" + amount + "&user_id=" + userId + "&page=" + page;

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
        String url = "/battles/open-voting?page="+page;

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

        String responseString =  ConnectionController.sendData(url, "video", fileFormat, stream);
        System.out.println("UploadRound response: " + responseString);
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
        String url = "/battle" + battleId + "/vote";
        VoteRequest request = new VoteRequest();
        request.setRapper_numer(rapperNumber);

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
