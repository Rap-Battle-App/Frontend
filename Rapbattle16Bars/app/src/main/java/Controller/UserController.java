package Controller;

/**
 * Created by Dennis on 03.11.2015.
 */
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
    public static void setUsername(User user, String newUserName) throws JSONException {
        //TODO: JSON verschicken
        JSONObject userNameObj = new JSONObject("\"username\":\""+newUserName+"\"");
    }

    /**
     * Changes the Rapperstatus of an User in the Database
     * @param user the User
     * @param isRapper the new status
     */
    public static void setIsRapper(User user, boolean isRapper)throws JSONException{
        setSettings(user,user.getNotifications(),isRapper);
    }

    /**
     * Changes the Notificationstatus of an User in the Database
     * @param user the User
     * @param notifications the new status
     */
    public static void setNotifications(User user, boolean notifications) throws JSONException {
        setSettings(user,notifications,user.getIsRapper());
    }

    public static void setSettings(User user, boolean notifications, boolean isRapper) throws JSONException {
        JSONObject notificationObj = new JSONObject("{\"rapper\":\"\"+isRapper+\"\", \"notifications\":\""+notifications+"\"}");
        //TODO:JSON verschicken
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
        }
        else if(username.equals("testViewer")){
            settingsJson = new JSONObject("\"rapper\":\"true\", \"notifications\":\"true\"");
        }
        else{
            return null;
        }

        settings = new Settings(settingsJson.getBoolean("notifications"),settingsJson.getBoolean("rapper"));
        return settings;
    }

    public static void setProfilPicture(String username, byte picture) throws JSONException{
        JSONObject pictureJSON = new JSONObject("{\"picture\":\"" + picture + "\"}");
        //TODO:Logik erstellen

    }

    public static void setProfileInformation(String username, String location, String aboutMe) throws JSONException {
        JSONObject profilInformation = new JSONObject("\"city\":\""+location+"\", \"about_me\":\""+aboutMe+"\"");
        //TODO: JSON an api senden
    }

    /**
     *
     * @param username
     * @return returns a Rapper if username equals "testRapper", returns a Viewer if username equals "testViewer", esle null
     */
    public static User getUser(String username) throws JSONException {
        //{"id":"3", "username":"testRapper", "profile_picture":"blablabla", "city":"Hopsten", "about_me":"cooler Dude, yo", "statistics":{"wins":"10","looses":"13"}, "rapper":"true"}

        User user;
        //TODO: JSON aus Api erhalten
        JSONObject UserJSON;
        if(username.equals("testRapper")) {
            UserJSON = new JSONObject("{\"id\":\"3\", \"username\":\"testRapper\", \"profile_picture\":\"blablabla\", \"city\":\"Hopsten\", \"about_me\":\"cooler Dude, yo\","
                    + " \"statistics\":{\"wins\":\"10\",\"looses\":\"13\"}, \"rapper\":\"true\"}");
        }
        else if(username.equals("testViewer")){
            UserJSON = new JSONObject("{\"id\":\"2\", \"username\":\"testViewer\", \"profile_picture\":\"blablabla2\", \"city\":\"Osnabrück\", \"about_me\":\"nicht son cooler Dude, yo\","
                    + " \"statistics\":\"null\", \"rapper\":\"false\"}");
        }
        else{
            return null;
        }

        String newUsername = UserJSON.getString("username");
        String profilePicture = UserJSON.getString("profile_picture");
        String location = UserJSON.getString("city");
        String aboutMe = UserJSON.getString("about_me");
        JSONObject stats = UserJSON.getJSONObject("statistics");
        int wins = stats.getInt("wins");
        int looses = stats.getInt("looses");
        boolean isRapper = UserJSON.getBoolean("rapper");

        Rapper rapper = new Rapper(newUsername,wins,looses);
        user = new User(username, location, aboutMe, profilePicture,isRapper,true, rapper );

        return user;
    }
}
