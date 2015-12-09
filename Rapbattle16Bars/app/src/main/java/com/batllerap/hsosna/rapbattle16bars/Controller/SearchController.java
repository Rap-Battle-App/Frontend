package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.Profile.ProfilePreview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

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
    public static ProfilePreview[] profileSearch(String searchString) throws JSONException, IOException {
        String url = "/search";
        JSONObject request = new JSONObject();
        request.put("search_string", searchString);

        JSONObject response = new JSONObject(ConnectionController.getJSON(url, request));
        JSONArray responses = response.getJSONArray("profiles");

        ProfilePreview[] previews = new ProfilePreview[responses.length()];

        for(int i = 0; i < response.length(); i++) {
            previews[i] = ObjectParser.parseProfliePreview(responses.getJSONObject(i));
        }

        return previews;
    }
}
