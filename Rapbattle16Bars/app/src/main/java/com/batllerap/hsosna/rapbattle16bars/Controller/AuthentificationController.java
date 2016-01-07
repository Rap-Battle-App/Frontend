package com.batllerap.hsosna.rapbattle16bars.Controller;

/**
 * Created by Dennis on 03.11.2015.
 */


import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.batllerap.hsosna.rapbattle16bars.Model.request.LoginRequest;
import com.batllerap.hsosna.rapbattle16bars.Model.request.ResetRequest;
import com.batllerap.hsosna.rapbattle16bars.Model.response.LoginResponse;
import com.batllerap.hsosna.rapbattle16bars.Model.request.RegisterRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.io.IOException;

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

        int userId = -1;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);

        String requestString = gson.toJson(request);

        String responseString = ConnectionController.postJSON(url, requestString);
        System.out.println("Login response: " + responseString);

        LoginResponse login = gson.fromJson(responseString, LoginResponse.class);
        System.out.println("Login: ResponseID: " + login.getUser_id() + " from JSON: " +responseString);
        userId = login.getUser_id();
        if(userId >= 0) {
            System.out.println("Login: Login ERFOLGREICH!!");
            return UserController.getUser(userId);
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
    public static User register(String username, String email, String password)throws IOException {
        String url = "/auth/register";
        int userId = -1;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        RegisterRequest request = new RegisterRequest();
        request.setEmail(email);
        request.setUsername(username);
        request.setPassword(password);

        String requestJSON = gson.toJson(request);

        String responseString = ConnectionController.postJSON(url, requestJSON);
        System.out.println( "Auth response: " +responseString);
        try {
            LoginResponse login = gson.fromJson(responseString, LoginResponse.class);
            System.out.println("Auth: RegisterResponseID: " + login.getUser_id());
            userId = login.getUser_id();
            if (userId >= 0) {
                System.out.println("Register ERFOLGREICH!!");
                return UserController.getUser(userId);
            }
        }
        catch(Exception ex) {
            throw ex;
        }
        return null;
    }

    /**
     * Logout
     * @return returns true if logout is successful, else false
     */
    public static void logout() throws IOException {
        String url = "/auth/logout";
        String responseString = ConnectionController.getJSON(url);
    }

    /**
     * tells the Backend to reset a Password for an Account
     * @param email Email to find the Account
     * @return returns true if reset is successful, else false
     */
    public static void resetPassword(String email, String password, String token) throws IOException{
        String url = "/password-recovery/reset";
        ResetRequest request = new ResetRequest();
        request.setEmail(email);
        request.setPassword(password);
        request.setToken(token);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String requestString = gson.toJson(request);
        String responseString = ConnectionController.postJSON(url,requestString);
        System.out.println("ResetPassword response: " + responseString);
    }


}
