package com.batllerap.hsosna.rapbattle16bars.Model;

/**
 * Created by Dennis on 04.11.2015.
 */
public class BattlePreview {
    int battleId;
    ProfilePreview rapper1;
    ProfilePreview rapper2;

    public int getBattleId(){
        return this.battleId;
    }

    public ProfilePreview getRapper1(){
        return this.rapper1;
    }

    public ProfilePreview getRapper2(){
        return this.rapper2;
    }

    public BattlePreview(int id, int userId1, String username1, String profilePicture1, int userId2, String username2, String profilePicture2){
        this.battleId = id;
        this.rapper1 = new ProfilePreview(userId1,username1,profilePicture1);
        this.rapper2 = new ProfilePreview(userId2,username2,profilePicture2);
    }
}
