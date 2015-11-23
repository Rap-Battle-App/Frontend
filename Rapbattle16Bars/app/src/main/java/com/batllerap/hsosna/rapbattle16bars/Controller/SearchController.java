package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.ProfilePreview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by woors on 23.11.2015.
 */

public class SearchController {
    /**
     * Searches for Users in the Database
     * @param searchString The Argument to search for
     * @return returns an Array of Profiles wich are similar to searchString
     * @throws JSONException
     */
    public static ProfilePreview[] profileSearch(String searchString) throws JSONException {
        JSONObject request = new JSONObject();
        request.put("search_string", searchString);

        //TODO: Request senden und JSON empfangen

        JSONObject response = new JSONObject("{\"prfoiles\": [" +
                "{\"user_id\":0, \"username\": \"testRapper\", \"profile_picture\":\"irgendwo\"}," +
                "{\"user_id\":1, \"username\": \"testRapper2\", \"profile_picture\":\"irgendwo2\"}," +
                "{\"user_id\":2, \"username\": \"testRapper2\", \"profile_picture\":\"irgendwo\"}" +
                "]}");
        JSONArray users = response.getJSONArray("profiles");

        ProfilePreview[] previews = new ProfilePreview[users.length()];
        for(int i = 0; i < users.length(); i++){
            JSONObject currentUser = users.getJSONObject(i);
            int userId = currentUser.getInt("user_id");
            String username = currentUser.getString("username");
            String profilePicture = currentUser.getString("profile_picture");

            previews[i] = new ProfilePreview(userId,username,profilePicture);
        }
        return previews;
    }
}
