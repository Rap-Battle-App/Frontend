package com.batllerap.hsosna.rapbattle16bars.Controller;

/**
 * Created by Dennis on 03.11.2015.
 */

import com.batllerap.hsosna.rapbattle16bars.Model.profile2.Rapper;
import com.batllerap.hsosna.rapbattle16bars.Model.request.PasswordRequest;
import com.batllerap.hsosna.rapbattle16bars.Model.request.ProfileInformationRequest;
import com.batllerap.hsosna.rapbattle16bars.Model.request.ProfilePictureRequest;
import com.batllerap.hsosna.rapbattle16bars.Model.Settings;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.batllerap.hsosna.rapbattle16bars.Model.Profile;
import com.batllerap.hsosna.rapbattle16bars.Model.request.UsernameRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.MalformedURLException;

public class UserController {
    /**
     * Changes the username in the Database
     *
     * @param user        user to find in Database
     * @param newUserName new Username
     */
    public static boolean setUsername(User user, String newUserName) throws  MalformedURLException, IOException {
        String url = "/account/username";

        UsernameRequest request = new UsernameRequest();
        request.setUsername(newUserName);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String requestString = gson.toJson(request);

        String responseString = ConnectionController.postJSON(url, requestString);

        System.out.println("SetUsername: response: " + responseString);
        user.setUserName(newUserName);
        return true;
    }

    /**
     * Changes the Rapperstatus of an User in the Database
     *
     * @param user     the User
     * @param isRapper the new status
     */
    public static boolean setIsRapper(User user, boolean isRapper) throws  IOException {
        return setSettings(user, user.getNotifications(), isRapper);
    }

    /**
     * Changes the Notificationstatus of an User in the Database
     *
     * @param user          the User
     * @param notifications the new status
     */
    public static boolean setNotifications(User user, boolean notifications) throws  IOException {
        return setSettings(user, notifications, user.isRapper());
    }

    public static boolean setSettings(User user, boolean notifications, boolean isRapper) throws  IOException {
        String url = "/account/settings";
        Settings settings = new Settings(notifications,isRapper);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String requestString = gson.toJson(settings);

        String responseString = ConnectionController.postJSON(url, requestString);
        System.out.println(responseString);

        user.setIsRapper(isRapper);
        user.setNotifications(notifications);
        return true;
    }

    /**
     * get Settings from an User
     *
     * @param username username
     * @return returns Setting from the User, if the User doesn't exist it returns null
     */
    public static Settings getSettings(String username) throws  IOException, MalformedURLException {
        String url = "/account/settings";

        String responseString = ConnectionController.getJSON(url);
        System.out.println("getSettings response: " + responseString);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(responseString, Settings.class);
    }

    public static boolean setProfilPicture(byte picture) throws  IOException {
        String url = "/profile/picture";

        ProfilePictureRequest request = new ProfilePictureRequest();
        request.setPicture(picture);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String requestString = gson.toJson(request);

        String responseString =  ConnectionController.postJSON(url, requestString);
        System.out.println("setProfilePicture: " + responseString);
        return true;
    }

    public static boolean setProfileInformation(User user, String location, String aboutMe) throws  IOException {
        String url = "/profile";

        ProfileInformationRequest request = new ProfileInformationRequest();
        request.setAbout_me(aboutMe);
        request.setCity(location);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String requestString = gson.toJson(request);

        String responseString =  ConnectionController.postJSON(url, requestString);
        System.out.println("setProfileInformation: response: " + responseString);

        user.setAboutMe(aboutMe);
        user.setLocation(location);
        return true;
    }

    public static boolean setLocation(User user, String location) throws  IOException {
        return UserController.setProfileInformation(user, location, user.getAboutMe());
    }

    public static boolean setAboutMe(User user, String aboutMe) throws IOException {
        return UserController.setProfileInformation(user, user.getLocation(), aboutMe);
    }

    /**
     * @param userId
     * @return returns a Rapper if username equals "testRapper", returns a Viewer if username equals "testViewer", esle null
     */
    public static User getUser(int userId) throws IOException{
        System.out.println("GetUser: userId: " + userId);
        String url = "/user/" + userId;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();


        String userJSON = ConnectionController.getJSON(url);

        Profile prof = gson.fromJson(userJSON, Profile.class);

        User user = new User();
        user.setId(prof.getId());
        user.setUserName(prof.getUsername());
        user.setAboutMe(prof.getAbout_me());
        user.setIsRapper(prof.isRapper());
        user.setLocation(prof.getCity());
        user.setProfilePicture(prof.getProfile_picture());
        Rapper rapper = null;
        if(user.isRapper()){
            rapper = new Rapper(user.getUserName(),prof.getStatistics().getWins(),prof.getStatistics().getDefeats());
        }
        user.setRapper(rapper);
        return user;
    }

    /**
     * Changes the Password of the current User
     *
     * @param oldPassword oldPassword of the current User
     * @param newPassword the new Password
     * @return returns true if successfull
     */
    public static boolean changePassword(String oldPassword, String newPassword) throws IOException {
        String url = "/account/password";

        PasswordRequest request = new PasswordRequest();
        request.setPassword(newPassword);
        request.setOld_password(oldPassword);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String requestString = gson.toJson(request);

        String responseString = ConnectionController.postJSON(url, requestString);
        System.out.println("ChangePassword response: " + responseString);

        return true;
    }
}
