package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Voting;
import com.batllerap.hsosna.rapbattle16bars.Model.Profile.ProfilePreview;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by woors on 09.12.2015.
 */
public class ObjectParser {

    public static Voting parseVoting(JSONObject json) throws JSONException {
        int votesRapper1 = json.getInt("votes_rapper1");
        int votesRapper2 = json.getInt("votes_rapper2");
        int votedFor = json.getInt("voted_for");
        boolean open = json.getBoolean("open");
        return new Voting(votesRapper1,votesRapper2,votedFor,open);
    }


    public static ProfilePreview parseProfliePreview(JSONObject json) throws JSONException{
        int id = json.getInt("user_id");
        String name = json.getString("username");
        String profilePicture = json.getString("profile_picture");

        return new ProfilePreview(id,name,profilePicture);
    }
}
