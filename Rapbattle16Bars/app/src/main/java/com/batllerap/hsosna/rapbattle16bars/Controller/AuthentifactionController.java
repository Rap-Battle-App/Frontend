package com.batllerap.hsosna.rapbattle16bars.Controller;

/**
 * Created by Dennis on 03.11.2015.
 */

import com.batllerap.hsosna.rapbattle16bars.Exceptions.AuthentificationException;
import com.batllerap.hsosna.rapbattle16bars.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthentifactionController {

    /**
     * Login
     * @param username username
     * @param password password
     * @return returns a Rapper if username equals "testRapper", returns a Viewer if username equals "testViewer", esle null
     */
    public static User login(String username, String password) throws AuthentificationException{
        //TODO: Login JSON verschicken + Antwort erhalten und success setzten
        User user;
        try {
            JSONObject loginObj = new JSONObject("\"username\":\"" + username + "\", \"password\":\"" + password + "\"");
        }
        catch (JSONException e){
            e.printStackTrace();
            throw new AuthentificationException("Error at making a JSONObjekt");
        }
        boolean success = true;

        if(success) {
            if (username.equals("testRapper")) {
                try {
                    user = UserController.getUser(username);
                }
                catch(Exception e){
                    e.printStackTrace();
                    throw new AuthentificationException(e.getMessage());
                }
            } else if (username.equals("testViewer")) {
                try {
                    user = UserController.getUser(username);
                }
                catch(Exception e){
                    e.printStackTrace();
                    throw new AuthentificationException(e.getMessage());
                }
            } else {
                return null;
            }
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
    public static User register(String username, String email, String password) throws Exception {
        //TODO: Register JSON verschicken + Antwort erhalten und success setzten
        JSONObject registerJSON = new JSONObject("\"username\":\"" + username + "\", \"email\":\"" + email + "\", \"password\":\"" + password + "\"");
        boolean success = true;

        if(success) {
            if (username.equals("testRapper")) {
                return UserController.getUser(username);
            } else if (username.equals("testViewer")) {
                return UserController.getUser(username);
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * Logout
     * @param username username
     * @return returns true if logout is successful, else false
     */
    public static boolean logout(String username){
        //TODO: Logout logik erstellen
        return true;
    }

    /**
     * tells the Backend to reset a Password for an Account
     * @param email Email to find the Account
     * @return returns true if reset is successful, else false
     */
    public static boolean resetPassword(String email){
        //TODO: resetPassword logik erstellen
        return true;
    }

}
