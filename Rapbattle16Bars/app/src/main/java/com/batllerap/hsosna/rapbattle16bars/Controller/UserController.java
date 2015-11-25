package com.batllerap.hsosna.rapbattle16bars.Controller;

/**
 * Created by Dennis on 03.11.2015.
 */
import com.batllerap.hsosna.rapbattle16bars.Exceptions.UserControllerException;
import com.batllerap.hsosna.rapbattle16bars.Model.Rapper;
import com.batllerap.hsosna.rapbattle16bars.Model.Settings;
import com.batllerap.hsosna.rapbattle16bars.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class UserController {

    /**
     * Changes the username in the Database
     * @param user user to find in Database
     * @param newUserName new Username
     */
    public static boolean setUsername(User user, String newUserName) throws JSONException {
        //TODO: JSON verschicken
        JSONObject userNameObj = new JSONObject("\"username\":\""+newUserName+"\"");
        user.setUserName(newUserName);
        return true;
    }

    /**
     * Changes the Rapperstatus of an User in the Database
     * @param user the User
     * @param isRapper the new status
     */
    public static boolean setIsRapper(User user, boolean isRapper)throws JSONException{
        return setSettings(user, user.getNotifications(),isRapper);
    }

    /**
     * Changes the Notificationstatus of an User in the Database
     * @param user the User
     * @param notifications the new status
     */
    public static boolean setNotifications(User user, boolean notifications) throws JSONException {
        return setSettings(user, notifications, user.getIsRapper());
    }

    public static boolean setSettings(User user, boolean notifications, boolean isRapper) throws JSONException {
        JSONObject notificationObj = new JSONObject("{\"rapper\":\"\"+isRapper+\"\", \"notifications\":\""+notifications+"\"}");
        //TODO:JSON verschicken
        user.setIsRapper(isRapper);
        user.setNotifications(notifications);
        return true;
    }

    /**
     * get Settings from an User
     * @param username username
     * @return returns Setting from the User, if the User doesn't exist it returns null
     */
    public static Settings getSettings(String username) throws JSONException {
        JSONObject settingsJson;
        //TODO: JSON empfangen

        Settings settings;
        if(username.equals("testRapper")) {
            settingsJson = new JSONObject("\"rapper\":\"true\", \"notifications\":\"true\"");

            //TODO: entfernen
            return new Settings(true,true);
        }
        else if(username.equals("testViewer")){
            settingsJson = new JSONObject("\"rapper\":\"true\", \"notifications\":\"true\"");

            //TODO: entfernen
            return new Settings(true,false);
        }
        else{
            return null;
        }

        //TODO: settings = new Settings(settingsJson.getBoolean("notifications"),settingsJson.getBoolean("rapper"));
        //return settings;
    }

    public static boolean setProfilPicture(byte picture) throws JSONException{
        JSONObject pictureJSON = new JSONObject("{\"picture\":\"" + picture + "\"}");
        //TODO:Logik erstellen
        return true;
    }

    public static boolean setProfileInformation(User user, String location, String aboutMe) throws JSONException {
        JSONObject profilInformation = new JSONObject("\"city\":\""+location+"\", \"about_me\":\""+aboutMe+"\"");
        //TODO: JSON an api senden
        user.setAboutMe(aboutMe);
        user.setLocation(location);
        return true;
    }

    /**
     *
     * @param username
     * @return returns a Rapper if username equals "testRapper", returns a Viewer if username equals "testViewer", esle null
     */
    public static User getUser(int  userId) throws UserControllerException {
        //{"id":"3", "username":"testRapper", "profile_picture":"blablabla", "city":"Hopsten", "about_me":"cooler Dude, yo", "statistics":{"wins":"10","looses":"13"}, "rapper":"true"}

        User user = null;
        //TODO: JSON aus Api erhalten
        JSONObject UserJSON = null;
        if(userId == 0) {
            System.out.println("rapper");
            try {
                UserJSON = new JSONObject("{\"id\":3, \"username\":\"testRapper\", \"profile_picture\":\"blablabla\", \"city\":\"Hopsten\", \"about_me\":\"cooler Dude, yo\","
                        + " \"statistics\":{\"wins\":10,\"looses\":13}, \"rapper\":\"TRUE\" }");
                Rapper rapper = new Rapper("testRapper",10,22);
                //TODO: entfernen
                return new User(0, "testRapper",null,null,null,true,true,rapper);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(userId == 1){
            System.out.println("viewer");
            try {
                UserJSON = new JSONObject("{\"id\":2, \"username\":\"testViewer\", \"profile_picture\":\"blablabla2\", \"city\":\"Osnabrück\", \"about_me\":\"nicht son cooler Dude, yo\","
                        + " \"statistics\":NULL, \"rapper\":\"FALSE\"}");
                //TODO: entfernen
                return new User(1, "testViewer",null,null,null,false,true,null);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Kein User gefunden");
            return null;
        }
        try {
            String ParsingData = "{\"id\":\"alasdj\"}";
            JSONObject jsonObject = new JSONObject(ParsingData);
            String name = jsonObject.getString("id");
            System.out.println(name);

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
            user = new User(id, newUsername, location, aboutMe, profilePicture,isRapper,true, rapper );
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Changes the Password of the current User
     * @param oldPassword oldPassword of the current User
     * @param newPassword the new Password
     * @return returns true if successfull
     * @throws JSONException
     */
    public static boolean changePassword(String oldPassword, String newPassword) throws JSONException{
        JSONObject obj = new JSONObject();
        obj.put("old_password",oldPassword);
        obj.put("password",newPassword);

        //TODO: JSON versenden!

        return true;
    }
}
