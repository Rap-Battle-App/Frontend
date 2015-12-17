package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.ProfilePreview;
import com.batllerap.hsosna.rapbattle16bars.Model.request.SearchRequest;
import com.batllerap.hsosna.rapbattle16bars.Model.response.SearchResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;

/**
 * Created by woors on 23.11.2015.
 */

public class SearchController {
    /**
     * Searches for Users in the Database
     * @param searchString The Argument to search for
     * @return returns an Array of Profiles wich are similar to searchString
     * @throws IOException
     */
    public static ProfilePreview[] profileSearch(String searchString) throws IOException {
        String url = "/search";

        SearchRequest request = new SearchRequest();
        request.setSearch_string(searchString);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String requestString = gson.toJson(request);

        String responseString = ConnectionController.postJSON(url, requestString);
        System.out.println("profileSearch response: " + responseString);

        SearchResponse response = gson.fromJson(responseString, SearchResponse.class);

        return response.getProfiles();
    }
}
