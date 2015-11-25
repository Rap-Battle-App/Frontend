package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.BattlePreview;
import com.batllerap.hsosna.rapbattle16bars.Model.OpenBattle;
import com.batllerap.hsosna.rapbattle16bars.Model.PhaseInfo.Phase1Info;
import com.batllerap.hsosna.rapbattle16bars.Model.PhaseInfo.Phase2Info;
import com.batllerap.hsosna.rapbattle16bars.Model.PhaseInfo.PhaseInfo;
import com.batllerap.hsosna.rapbattle16bars.Model.ProfilePreview;
import com.batllerap.hsosna.rapbattle16bars.Model.RequestList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    public static BattlePreview[] getTrendingBattles(int page, int amount) throws JSONException {
        JSONObject request = new JSONObject();
        request.put("page",page);
        request.put("amount", amount);


        //TODO: Request senden und JSON empfangen


        JSONObject trendingBattles = new JSONObject("{\"total\":\"3\", \"per_page\":\"4\", \"current_page\":\"0\", \"last_page\":\"0\",\"next_page_url\":\"blablabla\","
                + " \"prev_page_url\":\"albalbalb\", \"from\":\"0\", \"to\":\"0\", \"data\":[{\"battle_id\":\"1\",\"rapper1\":{\"user_id\":\"3\", \"username\":\"testRapper\", "
                + "\"profile_picture\":\"blablabla1\"}, \"rapper2\":{\"user_id\":\"2\", \"username\":\"testRapper2\", \"profile_picture\":\"blablabla2\"}}]}");

        return parseBattleList(trendingBattles);
    }

    /**
     * Returns a BattleList Array with open for Voting Battles
     * @param count maximum of Battles in the BattleList
     * @return
     */
    public static BattlePreview[] getOpenForVotingBattles(int userId, int page, int amount) throws JSONException {
        JSONObject request = new JSONObject();
        request.put("user_id",userId);
        request.put("page", page);
        request.put("amount", amount);

        //TODO: Request senden und JSON empfangen

        JSONObject trendingBattles = new JSONObject("{\"total\":\"3\", \"per_page\":\"4\", \"current_page\":\"0\", \"last_page\":\"0\",\"next_page_url\":\"blablabla\","
                + " \"prev_page_url\":\"albalbalb\", \"from\":\"0\", \"to\":\"0\", \"data\":[{\"battle_id\":\"1\",\"rapper1\":{\"user_id\":\"3\", \"username\":\"testRapper\", "
                + "\"profile_picture\":\"blablabla1\"}, \"rapper2\":{\"user_id\":\"2\", \"username\":\"testRapper2\", \"profile_picture\":\"blablabla2\"}}]}");

        return parseBattleList(trendingBattles);
    }

    /**
     * Returns a BattleList Array with completed Battles
     * @param count maximum of Battles in the BattleList
     * @return
     */
    public static BattlePreview[] getCompletedBattles(int userId, int page, int amount) throws JSONException {
        JSONObject request = new JSONObject();
        request.put("user_id", userId);
        request.put("page", page);
        request.put("amount", amount);

        //TODO: Request senden und JSON empfangen

        JSONObject trendingBattles = new JSONObject("{\"total\":\"3\", \"per_page\":\"4\", \"current_page\":\"0\", \"last_page\":\"0\",\"next_page_url\":\"blablabla\","
                + " \"prev_page_url\":\"albalbalb\", \"from\":\"0\", \"to\":\"0\", \"data\":[{\"battle_id\":\"1\",\"rapper1\":{\"user_id\":\"3\", \"username\":\"testRapper\", "
                + "\"profile_picture\":\"blablabla1\"}, \"rapper2\":{\"user_id\":\"2\", \"username\":\"testRapper2\", \"profile_picture\":\"blablabla2\"}}]}");

        return parseBattleList(trendingBattles);
    }

    /**
     * Returns a BattleList Array with open Battles
     * @param count maximum of Battles in the BattleList
     * @return
     */
    public static BattlePreview[] getOpenBattles(int page) throws JSONException {
        JSONObject request = new JSONObject();
        request.put("page", page);

        //TODO: Request senden und JSON empfangen

        JSONObject trendingBattles = new JSONObject("{\"total\":\"3\", \"per_page\":\"4\", \"current_page\":\"0\", \"last_page\":\"0\",\"next_page_url\":\"blablabla\","
                + " \"prev_page_url\":\"albalbalb\", \"from\":\"0\", \"to\":\"0\", \"data\":[{\"battle_id\":\"1\",\"rapper1\":{\"user_id\":\"3\", \"username\":\"testRapper\", "
                + "\"profile_picture\":\"blablabla1\"}, \"rapper2\":{\"user_id\":\"2\", \"username\":\"testRapper2\", \"profile_picture\":\"blablabla2\"}}]}");

        return parseBattleList(trendingBattles);
    }


    /**
     * Returns a completed or open for Voting Battle
     * @param battleId if id == 0 returns a completed Battle, if id==1 returns a open for Voting battle else null
     * @return
     */
    public static Battle getBattle(int battleId) throws JSONException{
        //TODO: JSON Empfangen
        JSONObject battleJSON;
        if(battleId == 0) {
            battleJSON = new JSONObject("{  \n" +
                    "   \"id\":\"0\",\n" +
                    "   \"video_url\":\"blublbulblub\",\n" +
                    "   \"rapper1\":{  \n" +
                    "      \"user_id\":\"3\",\n" +
                    "      \"username\":\"testRapper\",\n" +
                    "      \"profile_picture\":\"blablabla1\"\n" +
                    "   },\n" +
                    "   \"rapper2\":{  \n" +
                    "      \"user_id\":\"2\",\n" +
                    "      \"username\":\"testRapper2\",\n" +
                    "      \"profile_picture\":\"blablabla2\"\n" +
                    "   },\n" +
                    "   \"voting\":{  \n" +
                    "      \"votes_rapper1\":\"10\",\n" +
                    "      \"votes_rapper2\":\"8\",\n" +
                    "      \"voted_for\":\"1\",\n" +
                    "      \"open\":\"false\"\n" +
                    "   }\n" +
                    "}");
        }
        else if(battleId == 1){
            battleJSON = new JSONObject("{  \n" +
                    "   \"id\":\"1\",\n" +
                    "   \"video_url\":\"blublbulblub\",\n" +
                    "   \"rapper1\":{  \n" +
                    "      \"user_id\":\"3\",\n" +
                    "      \"username\":\"testRapper\",\n" +
                    "      \"profile_picture\":\"blablabla1\"\n" +
                    "   },\n" +
                    "   \"rapper2\":{  \n" +
                    "      \"user_id\":\"2\",\n" +
                    "      \"username\":\"testRapper2\",\n" +
                    "      \"profile_picture\":\"blablabla2\"\n" +
                    "   },\n" +
                    "   \"voting\":{  \n" +
                    "      \"votes_rapper1\":\"10\",\n" +
                    "      \"votes_rapper2\":\"8\",\n" +
                    "      \"voted_for\":\"1\",\n" +
                    "      \"open\":\"true\"\n" +
                    "   }\n" +
                    "}");
        }
        else{
            return null;
        }
        JSONObject rapper1 = battleJSON.getJSONObject("rapper1");
        JSONObject rapper2 = battleJSON.getJSONObject("rapper2");
        JSONObject voting = battleJSON.getJSONObject("voting");
        int id = battleJSON.getInt("id");
        boolean isOpen = voting.getBoolean("open");
        int votes1 = voting.getInt("votes_rapper1");
        int votes2 = voting.getInt("votes_rapper2");
        String rapper1Name = rapper1.getString("username");
        String rapper2Name = rapper2.getString("username");
        String videoUrl = battleJSON.getString("video_url");

        return new Battle(id, isOpen, votes1, votes2, rapper1Name, rapper2Name, null, null, videoUrl);
    }

    public static OpenBattle getOpenBattle(int battleId) throws JSONException{
        OpenBattle openBattle = null;

        //TODO: JSON Empfangen
        JSONObject response = new JSONObject("{\"id\":3, " +
                "\"opponent\":{\"user_id\":1, \"username\": \"testRapper\", \"profile_picture\":\"blskjdtfösa\"}, \"phase\": 1, \"info\":" +
                " {\"time_left\":561, \"round1_url\":\"irgendwo\", \"beat_id\": 4, \"opponent_round1_url\":\"nirgendwo\", \"round2_url\": \"nochwoanders\"}}");

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

    public static boolean uploadRound(int battleId, int beatId, byte[] video) throws JSONException {
        //TODO: Logik erstellen
        JSONObject obj = new JSONObject();
        obj.put("beat_id",beatId);
        obj.put("video", video);

        return true;
    }

    /**
     * Returns a RequestList of Requests for a Rapper
     * @param rapperName the name of the Rapper
     * @return
     */
    public static RequestList getRequestList(String rapperName){
        //TODO: Logik erstellen
        RequestList rqList;
        rqList = null;
        return rqList;
    }

    /**
     * Sends a Request to an opponent
     * @param rapperName
     * @param opponentName
     */
    public static boolean sendRequest(String rapperName, String opponentName) throws JSONException {
        //TODO: Logik erstellen
        JSONObject json = new JSONObject();
        json.put("user_id", opponentName);

        return true;
    }

    /**
     * sends a Request to a random Rapper
     * @param rapperName
     */
    public static void sendRequest(String rapperName) throws JSONException {
        //TODO: Logik erstellen
        JSONObject answerr = new JSONObject("{\"opponent\":{\"user_id\":1, \"username\":\"testRapper\",\"profile_picture\":\"blablabla\"}}");
        JSONObject opponent = answerr.getJSONObject("opponent");


        ProfilePreview opponentProfile = new ProfilePreview(opponent.getInt("user_id"),opponent.getString("username"), opponent.getString("profile_picture"));

    }

    /**
     * gives a vote for a Battle to the Database
     * @param rapper_number the name of the rapper, who gets the Vote
     */
    public static boolean voteBattle(int battleId, int rapperNumber) throws JSONException {
        JSONObject request = new JSONObject();
        request.put("rapper_numer", rapperNumber);

        //TODO: Logik erstellen

        return true;
    }

    /**
     * answers a Request
     * @param rapperName
     * @param opponentName
     * @param accepted true if the request is accepted, else false
     */
    public static void answerRequest(int requestId, boolean accepted){
        //TODO: Logik erstellen
    }
}
