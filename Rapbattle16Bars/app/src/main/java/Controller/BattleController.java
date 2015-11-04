package Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.BattlePreview;
import com.batllerap.hsosna.rapbattle16bars.Model.Beat;
import com.batllerap.hsosna.rapbattle16bars.Model.Rapper;
import com.batllerap.hsosna.rapbattle16bars.Model.RequestList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dennis on 03.11.2015.
 */
public class BattleController {


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
    public static BattlePreview[] getTrendingBattles(int count) throws JSONException {
        //TODO: JSON empfangen
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
    public static BattlePreview[] getOpenForVotingBattles(int count) throws JSONException {
        //TODO: JSON empfangen
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
    public static BattlePreview[] getCompletedBattles(int count) throws JSONException {
        //TODO: JSON empfangen
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
    public static BattlePreview[] getOpenBattles(int count) throws JSONException {
        //TODO: JSON empfangen
        JSONObject trendingBattles = new JSONObject("{\"total\":\"3\", \"per_page\":\"4\", \"current_page\":\"0\", \"last_page\":\"0\",\"next_page_url\":\"blablabla\","
                + " \"prev_page_url\":\"albalbalb\", \"from\":\"0\", \"to\":\"0\", \"data\":[{\"battle_id\":\"1\",\"rapper1\":{\"user_id\":\"3\", \"username\":\"testRapper\", "
                + "\"profile_picture\":\"blablabla1\"}, \"rapper2\":{\"user_id\":\"2\", \"username\":\"testRapper2\", \"profile_picture\":\"blablabla2\"}}]}");

        return parseBattleList(trendingBattles);
    }

    /**
     * gives a vote for a Battle to the Database
     * @param rapper_number the name of the rapper, who gets the Vote
     */
    public static void voteBattle(int rapper_number){
        //TODO: Logik erstellen
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
        int phase;
        boolean isOpen = voting.getBoolean("open");
        int votes1 = voting.getInt("votes_rapper1");
        int votes2 = voting.getInt("votes_rapper2");
        String rapper1Name = rapper1.getString("username");
        String rapper2Name = rapper2.getString("username");
        String videoUrl = battleJSON.getString("video_url");

        return new Battle(id, isOpen, votes1, votes2, rapper1Name, rapper2Name, null, null, videoUrl);
    }

    //TODO: public static void sendBattle(Beat beat, Video video){} DATEITYP FÜR VIDEO HERRAUSFINDEN

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
    public static void sendRequest(String rapperName, String opponentName){
        //TODO: Logik erstellen
    }

    /**
     * sends a Request to a random Rapper
     * @param rapperName
     */
    public static void sendRequest(String rapperName){
        //TODO: Logik erstellen
    }

    /**
     * answers a Request
     * @param rapperName
     * @param opponentName
     * @param accepted true if the request is accepted, else false
     */
    public static void answerRequest(String rapperName, String opponentName, boolean accepted){
        //TODO: Logik erstellen
    }
}
