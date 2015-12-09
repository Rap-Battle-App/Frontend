package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.BattlePreview;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.OpenBattle;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.PhaseInfo.Phase1Info;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.PhaseInfo.Phase2Info;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.PhaseInfo.PhaseInfo;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Request;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Voting;
import com.batllerap.hsosna.rapbattle16bars.Model.Profile.ProfilePreview;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.RequestList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

/**
 * Created by Dennis on 03.11.2015.
 */
public class BattleController {

    /**
     * parse a JSONObject to an Array of BattlePreview
     * @param obj
     * @return
     * @throws JSONException
     */
    private static BattlePreview[] parseBattleList(JSONObject obj) throws JSONException {
        JSONArray battlesJSON = obj.getJSONArray("data");
        BattlePreview[] battles = new BattlePreview[battlesJSON.length()];
        for(int i = 0; i < battlesJSON.length(); i++){
            JSONObject battle = battlesJSON.getJSONObject(i);
            int battleId = battle.getInt("battle_id");
            JSONObject user1 = battle.getJSONObject("rapper1");
            JSONObject user2 = battle.getJSONObject("rapper2");
            int userId1 = user1.getInt("user_id");
            int userId2 = user2.getInt("user_id");
            String username1 = user1.getString("username");
            String username2 = user2.getString("username");
            String profilePicture1 = user1.getString("profile_picture");
            String profilePicture2 = user2.getString("profile_picture");

            battles[i] = new BattlePreview(battleId, userId1, username1,profilePicture1, userId2, username2, profilePicture2);
        }
        return battles;
    }

    /**
     * Returns a BattleList Array with trendingBattles
     * @param count maximum of Battles in the BattleList
     * @return
     */
    public static BattlePreview[] getTrendingBattles(int page, int amount) throws JSONException, IOException {
        String url = "/battles/trending";
        JSONObject request = new JSONObject();
        request.put("page",page);
        request.put("amount", amount);

        JSONObject response = new JSONObject(ConnectionController.getJSON(url, request));

        return parseBattleList(response);
    }

    /**
     * Returns a BattleList Array with open for Voting Battles
     * @param count maximum of Battles in the BattleList
     * @return
     */
    public static BattlePreview[] getOpenForVotingBattles(int userId, int page, int amount) throws JSONException, IOException {
        String url = "/battles/open-voting";
        JSONObject request = new JSONObject();
        request.put("user_id",userId);
        request.put("page", page);
        request.put("amount", amount);

        JSONObject response = new JSONObject(ConnectionController.getJSON(url, request));

        return parseBattleList(response);
    }

    /**
     * Returns a BattleList Array with completed Battles
     * @param count maximum of Battles in the BattleList
     * @return
     */
    public static BattlePreview[] getCompletedBattles(int userId, int page, int amount) throws JSONException, IOException {
        String url = "/battles/completed";
        JSONObject request = new JSONObject();
        request.put("user_id", userId);
        request.put("page", page);
        request.put("amount", amount);

        JSONObject response = new JSONObject(ConnectionController.getJSON(url, request));

        return parseBattleList(response);
    }

    /**
     * Returns a BattleList Array with open Battles
     * @param count maximum of Battles in the BattleList
     * @return
     */
    public static BattlePreview[] getOpenBattles(int page) throws JSONException, IOException {
        String url = "/battles/open";
        JSONObject request = new JSONObject();
        request.put("page", page);

        JSONObject response = new JSONObject(ConnectionController.getJSON(url, request));

        return parseBattleList(response);
    }


    /**
     * Returns a completed or open for Voting Battle
     * @param battleId if id == 0 returns a completed Battle, if id==1 returns a open for Voting battle else null
     * @return
     */
    public static Battle getBattle(int battleId) throws JSONException, IOException {
        String url = "/battle/" + battleId;
        JSONObject response = new JSONObject(ConnectionController.getJSON(url,null));

        JSONObject rapper1 = response.getJSONObject("rapper1");
        JSONObject rapper2 = response.getJSONObject("rapper2");
        JSONObject voting = response.getJSONObject("voting");
        int id = response.getInt("id");
        ProfilePreview rapper1PP = parseProfliePreview(rapper1);
        ProfilePreview rapper2PP = parseProfliePreview(rapper2);
        String videoUrl = response.getString("video_url");
        Voting votings = parseVoting(voting);

        return new Battle(id, rapper1PP, rapper2PP, videoUrl, votings);
    }

    private static Voting parseVoting(JSONObject json) throws JSONException{
        int votesRapper1 = json.getInt("votes_rapper1");
        int votesRapper2 = json.getInt("votes_rapper2");
        int votedFor = json.getInt("voted_for");
        boolean open = json.getBoolean("open");
        return new Voting(votesRapper1,votesRapper2,votedFor,open);
    }

    private static ProfilePreview parseProfliePreview(JSONObject json) throws JSONException{
        int id = json.getInt("user_id");
        String name = json.getString("username");
        String profilePicture = json.getString("profile_picture");

        return new ProfilePreview(id,name,profilePicture);
    }

    public static OpenBattle getOpenBattle(int battleId) throws JSONException, IOException {
        String url = "/open-battle/" + battleId;
        OpenBattle openBattle = null;

        JSONObject response = new JSONObject(ConnectionController.getJSON(url, null));

        JSONObject opponent = response.getJSONObject("opponent");
        int phase = response.getInt("phase");
        JSONObject info = response.getJSONObject("info");

        int userId = opponent.getInt("user_id");
        String userName = opponent.getString("username");
        String profilePicture = opponent.getString("profile_picture");

        int timeLeft = info.getInt("time_left");
        String round1Url = info.getString("round1_url");

        PhaseInfo phaseInfo;

        if(phase == 2) {
            int beatId = info.getInt("beat_id");
            String opponent_round1Url = info.getString("opponent_round1_url");
            String round2Url = info.getString("round2_url");
            phaseInfo = new Phase2Info(timeLeft,round1Url,beatId,opponent_round1Url,round2Url);
        }
        else{
            phaseInfo = new Phase1Info(round1Url,timeLeft);
        }
        openBattle = new OpenBattle(battleId,userId,userName,profilePicture,phase,phaseInfo);

        return openBattle;
    }

    public static boolean uploadRound(int battleId, int beatId, byte[] video) throws JSONException, IOException {
        String url = "/open-battle/" + battleId + "/round";

        JSONObject obj = new JSONObject();
        obj.put("beat_id",beatId);
        obj.put("video", video);

        return ConnectionController.sendJSON(url, obj);
    }

    /**
     * Returns a RequestList of Requests for a Rapper
     * @param rapperName the name of the Rapper
     * @return
     */
    public static RequestList getRequestList(String rapperName) throws IOException, JSONException {
        String url = "/requests";

        JSONObject response = new JSONObject(ConnectionController.getJSON(url,null));
        JSONArray requestsJson = response.getJSONArray("requests");
        JSONArray opRqJson = response.getJSONArray("opponent_requests");

        Request[] requests = new Request[requestsJson.length()];
        Request[] oppRequests = new Request[opRqJson.length()];

        for(int i = 0; i < requestsJson.length(); i++){
            JSONObject currObj = requestsJson.getJSONObject(i);
            int id = currObj.getInt("id");
            ProfilePreview profile = parseProfliePreview(currObj.getJSONObject("opponent"));
            Date date = new Date(currObj.getInt("date"));
            requests[i] = new Request(id, profile, date);
        }
        for (int j = 0; j < opRqJson.length(); j++){
            JSONObject currObj = opRqJson.getJSONObject(j);
            int id = currObj.getInt("id");
            ProfilePreview profile = parseProfliePreview(currObj.getJSONObject("opponent"));
            Date date = new Date(currObj.getInt("date"));
            oppRequests[j] = new Request(id, profile, date);
        }
        RequestList rqList = new RequestList(requests, oppRequests);
        rqList = null;
        return rqList;
    }

    /**
     * Sends a Request to an opponent
     * @param rapperName
     * @param opponentName
     */
    public static ProfilePreview sendRequest() throws JSONException, IOException {
        String url = "/request/random";

        JSONObject json = new JSONObject(ConnectionController.getJSON(url, null));
        ProfilePreview opponent = parseProfliePreview(json);

        return opponent;
    }

    /**
     * sends a Request to a random Rapper
     * @param rapperName
     */
    public static boolean sendRequest(int userId) throws JSONException, IOException {
        String url = "/request";
        JSONObject request = new JSONObject();
        request.put("user_id", userId);

        return ConnectionController.sendJSON(url, request);

    }

    /**
     * gives a vote for a Battle to the Database
     * @param rapper_number the name of the rapper, who gets the Vote
     */
    public static boolean voteBattle(int battleId, int rapperNumber) throws JSONException, IOException {
        String url = "/battle" + battleId + "/vote";
        JSONObject request= new JSONObject();
        request.put("rapper_numer", rapperNumber);

        return ConnectionController.sendJSON(url, request);
    }

    /**
     * answers a Request
     * @param rapperName
     * @param opponentName
     * @param accepted true if the request is accepted, else false
     */
    public static boolean answerRequest(int id, boolean accepted) throws JSONException, IOException {
        String url = "/request" + id;
        JSONObject obj = new JSONObject();
        obj.put("accepted", accepted);

        return ConnectionController.sendJSON(url, obj);
    }
}
