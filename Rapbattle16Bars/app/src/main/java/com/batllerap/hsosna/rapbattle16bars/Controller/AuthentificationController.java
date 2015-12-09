package com.batllerap.hsosna.rapbattle16bars.Controller;

/**
 * Created by Dennis on 03.11.2015.
 */


import com.batllerap.hsosna.rapbattle16bars.Model.Profile.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

public class AuthentificationController {

    private static String token;

    public static String getToken(){
        return AuthentificationController.token;
    }

    /**
     * Login
     * @param username username
     * @param password password
     * @return returns a Rapper if username equals "testRapper", returns a Viewer if username equals "testViewer", esle null
     */
    public static User login(String username, String password) throws JSONException, IOException {
        String url = "/auth/login";

        User user;
        int userId = -1;

        JSONObject loginObj = new JSONObject();
        loginObj.put("username", username);
        loginObj.put("password", password);

        //TODO:Einkommentieren
        /*
        JSONObject response = new JSONObject(ConnectionController.getJSON(url, loginObj));
        userId = response.getInt("user_id");
        */

        //TODO:Entfernen
        if(username.equals("testRapper")) {
            userId = 0;
        }
        else if(username.equals("testViewer")){
            userId = 1;
        }
        // bis hier

        if(userId >= 0) {
            user = UserController.getUser(userId);
            return user;
        }
        return null;
    }

    /**
     * Creates a new User in the Database
     * @param username username
     * @param email email
     * @param password password
     * @return returns the new User
     */
    public static User register(String username, String email, String password) throws JSONException, IOException {
        String url = "/auth/register";
        int userId = -1;

        JSONObject registerJSON = new JSONObject();
        registerJSON.put("username", username);
        registerJSON.put("email", email);
        registerJSON.put("password", password);

        //TODO:Kommentar entfernen:
        /*
        JSONObject response = new JSONObject(ConnectionController.getJSON(url,registerJSON));
        userId = response.getInt("user_id");
        */

        //TODO:Entfernen
        if(username.equals("testRapper")){
            userId = 0;
        }
        else if(username.equals("testViewer")){
            userId = 1;
        }
        // bis hier

        if(userId >= 0) {
                return UserController.getUser(userId);
        }
        return null;
    }

    /**
     * Logout
     * @param username username
     * @return returns true if logout is successful, else false
     */
    public static boolean logout(String username) throws IOException {
        String url = "/auth/logout";
        return ConnectionController.sendJSON(url,null);
    }

    /**
     * tells the Backend to reset a Password for an Account
     * @param email Email to find the Account
     * @return returns true if reset is successful, else false
     */
    public static boolean resetPassword(String email, String passord) throws JSONException, IOException{
        String url = "/password-recovery/reset";
        JSONObject obj = new JSONObject();
        obj.put("email",email);
        obj.put("token", AuthentificationController.getToken());
        obj.put("password",passord);

        ConnectionController.sendJSON(url, obj);

        return true;
    }


}
