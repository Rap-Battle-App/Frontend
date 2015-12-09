package com.batllerap.hsosna.rapbattle16bars.Controller;

/**
 * Created by Dennis on 03.11.2015.
 */

import com.batllerap.hsosna.rapbattle16bars.Exceptions.UserControllerException;
import com.batllerap.hsosna.rapbattle16bars.Model.Profile.Rapper;
import com.batllerap.hsosna.rapbattle16bars.Model.Profile.Settings;
import com.batllerap.hsosna.rapbattle16bars.Model.Profile.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

public class UserController {
    /**
     * Changes the username in the Database
     *
     * @param user        user to find in Database
     * @param newUserName new Username
     */
    public static boolean setUsername(User user, String newUserName) throws JSONException, MalformedURLException, IOException {
        String url = "/account/username";
        boolean success = false;

        JSONObject userNameObj = new JSONObject();
        userNameObj.put("username", newUserName);

        //TODO: JSON verschicken
        //success = ConnectionController.sendJSON(url, userNameObj);
        //TODO: entfernen:
        success = true;

        user.setUserName(newUserName);
        return success;
    }

    /**
     * Changes the Rapperstatus of an User in the Database
     *
     * @param user     the User
     * @param isRapper the new status
     */
    public static boolean setIsRapper(User user, boolean isRapper) throws JSONException, IOException {
        return setSettings(user, user.getNotifications(), isRapper);
    }

    /**
     * Changes the Notificationstatus of an User in the Database
     *
     * @param user          the User
     * @param notifications the new status
     */
    public static boolean setNotifications(User user, boolean notifications) throws JSONException, IOException {
        return setSettings(user, notifications, user.getIsRapper());
    }

    public static boolean setSettings(User user, boolean notifications, boolean isRapper) throws JSONException, IOException {
        String url = "/account/settings";
        boolean success = false;

        JSONObject settingObj = new JSONObject();
        settingObj.put("rapper", isRapper);
        settingObj.put("notifications", notifications);

        //TODO:Kommentar entfernen
        //success = ConnectionController.sendJSON(url, settingObj);

        //TODO:Entfernen:
        success = true;

        user.setIsRapper(isRapper);
        user.setNotifications(notifications);
        return success;
    }

    /**
     * get Settings from an User
     *
     * @param username username
     * @return returns Setting from the User, if the User doesn't exist it returns null
     */
    public static Settings getSettings(String username) throws JSONException, IOException, MalformedURLException {
        String url = "/account/settings";
        //TODO: JSON empfangen
        /*JSONObject settingsJson = new JSONObject(ConnectionController.getJSON(url, null));
        boolean rapper = settingsJson.getBoolean("rapper");
        boolean notifications = settingsJson.getBoolean("notifications");
        return new Settings(notifications, rapper);*/

        //TODO: entfernen!
        Settings settings;
        if (username.equals("testRapper")) {
            return new Settings(true, true);
        } else if (username.equals("testViewer")) {
            return new Settings(true, false);
        } else {
            return null;
        }
        // bis hier
    }

    public static boolean setProfilPicture(byte picture) throws JSONException, IOException {
        String url = "/profile/picture";
        JSONObject pictureJSON = new JSONObject();
        pictureJSON.put("picture", picture);
        return ConnectionController.sendJSON(url, pictureJSON);
    }

    public static boolean setProfileInformation(User user, String location, String aboutMe) throws JSONException, IOException {
        String url = "/profile";
        boolean success = false;

        JSONObject profilInformation = new JSONObject();
        profilInformation.put("city", location);
        profilInformation.put("about_me", aboutMe);

        success = ConnectionController.sendJSON(url, profilInformation);

        user.setAboutMe(aboutMe);
        user.setLocation(location);
        return success;
    }

    public static boolean setLocation(User user, String location) throws JSONException, IOException {
        return UserController.setProfileInformation(user, location, user.getAboutMe());
    }

    public static boolean setAboutMe(User user, String aboutMe) throws JSONException, IOException {
        return UserController.setProfileInformation(user, user.getLocation(), aboutMe);
    }

    /**
     * @param userId
     * @return returns a Rapper if username equals "testRapper", returns a Viewer if username equals "testViewer", esle null
     */
    public static User getUser(int userId) throws IOException, JSONException {
        String url = "/user" + userId;
        User user = null;

        //TODO: entfernen
        if (userId == 0) {
            System.out.println("rapper");
                Rapper rapper = new Rapper("testRapper", 10, 22);
                return new User(0, "testRapper", null, null, null, true, true, rapper);
        } else if (userId == 1) {
            System.out.println("viewer");
                return new User(1, "testViewer", null, null, null, false, true, null);
        } else {
            System.out.println("Kein User gefunden");
            return null;
        }
        // bis hier

        //TODO: einkommentieren
        /*JSONObject UserJSON = new JSONObject(ConnectionController.getJSON(url, null));

        int id = UserJSON.getInt("id");
        String newUsername = UserJSON.getString("username");
        String profilePicture = UserJSON.getString("profile_picture");
        String location = UserJSON.getString("city");
        String aboutMe = UserJSON.getString("about_me");
        boolean isRapper = UserJSON.getBoolean("rapper");
        System.out.println("Rapper: " + isRapper);
        Rapper rapper = null;
        if (isRapper) {
            JSONObject stats = UserJSON.getJSONObject("statistics");
            int wins = stats.getInt("wins");
            int looses = stats.getInt("looses");
            rapper = new Rapper(newUsername, wins, looses);
        }
        user = new User(id, newUsername, location, aboutMe, profilePicture, isRapper, true, rapper);

        return user;*/
    }

    /**
     * Changes the Password of the current User
     *
     * @param oldPassword oldPassword of the current User
     * @param newPassword the new Password
     * @return returns true if successfull
     * @throws JSONException
     */
    public static boolean changePassword(String oldPassword, String newPassword) throws JSONException, IOException {
        String url = "/account/password";

        JSONObject obj = new JSONObject();
        obj.put("old_password", oldPassword);
        obj.put("password", newPassword);

        return ConnectionController.sendJSON(url, obj);
    }
}
