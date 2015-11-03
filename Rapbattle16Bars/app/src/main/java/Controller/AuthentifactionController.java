package Controller;

/**
 * Created by Dennis on 03.11.2015.
 */

import com.batllerap.hsosna.rapbattle16bars.Model.Rapper;
import com.batllerap.hsosna.rapbattle16bars.Model.User;

public class AuthentifactionController {

    /**
     * Login
     * @param username username
     * @param password password
     * @return return user if login is correct, else returns null
     */
    public static User login(String username, String password){
        User user;
        //TODO: login logik erstellen
        if(username.equals("testRapper")) {
            Rapper rapper = new Rapper(username,10,8,22);
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

    /**
     * Creates a new User in the Database
     * @param username username
     * @param email email
     * @param password password
     * @return returns the new User
     */
    public static User register(String username, String email, String password){
        User user = new User(username,"","", "", "dummyProfilPicture", false, true, null);
        //TODO: Registrierungslogik erstellen
        return user;
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
