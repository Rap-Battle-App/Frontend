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
     * @return
     */
    public static BattleListResponse getTrendingBattles(int page, int amount) throws IOException {
        String url = "/battles/trending";

        String responseString = ConnectionController.getJSON(url);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BattleListResponse response = gson.fromJson(responseString, BattleListResponse.class);

        return response;
    }

    /**
     * Returns a BattleList Array with open for Voting Battles
     * @param count maximum of Battles in the BattleList
     * @return
     */
    public static BattleListResponse getOpenForVotingBattles(int userId, int page, int amount) throws IOException {
        String url = "/battles/open-voting";

        String responseString = ConnectionController.getJSON(url);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BattleListResponse response = gson.fromJson(responseString, BattleListResponse.class);

        return response;
    }

    /**
     * Returns a BattleList Array with completed Battles
     * @param count maximum of Battles in the BattleList
     * @return
     */
    public static BattleListResponse getCompletedBattles(int userId, int page, int amount) throws IOException {
        String url = "/battles/open-voting";

        String responseString = ConnectionController.getJSON(url);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BattleListResponse response = gson.fromJson(responseString, BattleListResponse.class);

        return response;
    }

    /**
     * Returns a BattleList Array with open Battles
     * @param count maximum of Battles in the BattleList
     * @return
     */
    public static BattleListResponse getOpenBattles(int page) throws IOException {
        String url = "/battles/open-voting";

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

    public static boolean uploadRound(int battleId, int beatId, byte video) throws IOException {
        String url = "/open-battle/" + battleId + "/round";

        RoundRequest request = new RoundRequest();
        request.setBeat_id(beatId);
        request.setVideo(video);

        String requestString = getRequestString(request);

        String responseString =  ConnectionController.postJSON(url, requestString);
        System.out.println("UploadRound response: " + responseString);

        return true;
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
     * Sends a Request to an opponent
     * @param rapperName
     * @param opponentName
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
     * sends a Request to a random Rapper
     * @param rapperName
     */
    public static boolean sendRequest(int userId) throws IOException {
        String url = "/request";
        RequestRequest request = new RequestRequest();
        request.setUser_id(userId);

        String requestString = getRequestString(request);

        String responseString = ConnectionController.postJSON(url, requestString);
        System.out.println(responseString);
        return true;
    }

    /**
     * gives a vote for a Battle to the Database
     * @param rapper_number the name of the rapper, who gets the Vote
     */
    public static boolean voteBattle(int battleId, int rapperNumber) throws IOException {
        String url = "/battle" + battleId + "/vote";
        VoteRequest request = new VoteRequest();
        request.setRapper_numer(rapperNumber);

        String requestString = getRequestString(request);

        String responseString = ConnectionController.postJSON(url, requestString);
        System.out.println("voteBattle response: " +responseString);

        return true;
    }

    /**
     * answers a Request
     * @param rapperName
     * @param opponentName
     * @param accepted true if the request is accepted, else false
     */
    public static boolean answerRequest(int id, boolean accepted) throws IOException {
        String url = "/request" + id;
        AnswerRequest request = new AnswerRequest();
        request.setAccepted(accepted);

        String requestString = getRequestString(request);

        String responseString = ConnectionController.postJSON(url, requestString);
        System.out.println("AnswerRequest response: " + responseString);

        return true;
    }
}
