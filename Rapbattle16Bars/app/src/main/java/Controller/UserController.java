package Controller;

/**
 * Created by Dennis on 03.11.2015.
 */
import com.batllerap.hsosna.rapbattle16bars.Model.Rapper;
import com.batllerap.hsosna.rapbattle16bars.Model.Settings;
import com.batllerap.hsosna.rapbattle16bars.Model.User;

public class UserController {

    /**
     * Changes the username in the Database
     * @param user user to find in Database
     * @param newUserName new Username
     */
    public static void setUsername(User user, String newUserName){
        //TODO: logik erstellen
        user.setUserName(newUserName);
    }

    /**
     * Changes the Rapperstatus of an User in the Database
     * @param user the User
     * @param isRapper the new status
     */
    public static void setIsRapper(User user, boolean isRapper){
        //TODO: Logik erstellen
        Settings settings = new Settings(user.getNotifications(), isRapper);
        user.setIsRapper(isRapper);
    }

    /**
     * Changes the Notificationstatus of an User in the Database
     * @param user the User
     * @param notifications the new status
     */
    public static void setNotifications(User user, boolean notifications){
        //TODO: Logik erstellen
        Settings settings = new Settings(notifications, user.getIsRapper());
        user.setNotifications(notifications);
    }

    /**
     * get Settings from an User
     * @param username username
     * @return returns Setting from the User, if the User doesn't exist it returns null
     */
    public static Settings getSettings(String username){
        //TODO: Logik erstellen
        Settings settings;
        if(username.equals("testRapper")) {
            settings = new Settings(true, true);
        }
        else if(username.equals("testViewer")){
            settings = new Settings(true,false);
        }
        else{
            settings = null;
        }

        return settings;
    }

    public static void setProfilPicture(String username, String picture){
        //TODO:Logik erstellen

    }

    public static void setProfileInformation(String username, String location, String aboutMe){
        //TODO: Logik erstelln
    }

    /**
     *
     * @param username
     * @return returns a Rapper if username equals "testRapper", returns a Viewer if username equals "testViewer", esle null
     */
    public static User getUser(String username){
        User user;
        //TODO: Logik erstellen
        if(username.equals("testRapper")) {
            Rapper rapper = getRapper(username);
            user = new User(username, "testRealName", "testLocation", "testAboutme", "testProfilPicture", true, true, rapper);
        }
        else if(username.equals("testViewer")){
            user = new User(username, "testRealName", "testLocation", "testAboutme", "testProfilPicture", false, true, null);
        }
        else{
            user = null;
        }

        return user;
    }

    public static Rapper getRapper(String username){
        Rapper rapper;
        //TODO: Logik erstellen
        if(username.equals("testRapper")) {
            rapper = new Rapper(username,10,8,22);
        }
        else{
            rapper = null;
        }

        return rapper;
    }
}
