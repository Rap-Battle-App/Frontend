package Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.Beat;
import com.batllerap.hsosna.rapbattle16bars.Model.Rapper;
import com.batllerap.hsosna.rapbattle16bars.Model.RequestList;

/**
 * Created by Dennis on 03.11.2015.
 */
public class BattleController {
    /**
     * Returns a few trending Battles
     * @param count how many Battles
     * @return
     */
    public static Battle[] getTrendingBattles(int count){
        Battle[] battles = new Battle[count];
        //TODO: Logik erstellen
        return battles;
    }

    /**
     * Returns a few Battles, which are open for voting
     * @param count how many Battles
     * @return
     */
    public static Battle[] getOpenForVotingBattles(int count){
        Battle[] battles = new Battle[count];
        //TODO: Logik erstellen
        return battles;
    }

    /**
     * Returns a few completed Battles
     * @param count how many completed Battles
     * @return
     */
    public static Battle[] getCompletedBattles(int count){
        Battle[] battles = new Battle[count];
        //TODO: Logik erstellen
        return battles;
    }

    /**
     * Returns a few open Battles
     * @param count how many open Battles
     * @return
     */
    public static Battle[] getOpenBattles(int count){
        Battle[] battles = new Battle[count];
        //TODO: Logik erstellen
        return battles;
    }

    /**
     * gives a vote for a Battle to the Database
     * @param battleId the Id of the Voted Battle
     * @param rapperName the name of the rapper, who gets the Vote
     * @param voterName the name of the Voter
     */
    public static void voteBattle(int battleId, String rapperName, String voterName){
        //TODO: Logik erstellen
    }

    /**
     * Returns a Battle with Phase = battleId
     * @param battleId
     * @return
     */
    public static Battle getBattle(int battleId){
        Battle battle = null;
        int[] votes = {22,165};
        Rapper[] rapper = {new Rapper("testRapper0",0,0,0), new Rapper("testRapper1",0,0,0)};
        Beat[] beats = {new Beat("utzutzuzt",0), new Beat("töftöftöf",1)};
        if(battleId == 0) {
            battle = new Battle(battleId, 0, null, rapper, null);
        }
        else if(battleId == 1){
            battle = new Battle(battleId, 1, null, rapper, beats);
        }
        else if(battleId == 2){
            battle = new Battle(battleId, 2, null, rapper, beats);
        }
        else if(battleId == 3){
            battle = new Battle(battleId, 3, votes, rapper, beats);
        }
        else if(battleId == 4){
            battle = new Battle(battleId, 4, votes, rapper, beats);
        }
        //TODO: Logik erstellen
        return battle;
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
