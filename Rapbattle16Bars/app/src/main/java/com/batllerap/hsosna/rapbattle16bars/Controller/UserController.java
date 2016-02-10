package com.batllerap.hsosna.rapbattle16bars.Controller;

/**
 * Created by Dennis on 03.11.2015.
 */

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.android.internal.http.multipart.MultipartEntity;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class UserController{
    /**
     * Changes the username in the Database
     *
     * @param user        user to find in Database
     * @param newUserName new Username
     */

    static int serverResponseCode = 0;

    public static void setUsername(User user, String newUserName) throws  MalformedURLException, IOException {
        String url = "/account/username";

        UsernameRequest request = new UsernameRequest();
        request.setUsername(newUserName);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String requestString = gson.toJson(request);

        String responseString = ConnectionController.postJSON(url, requestString);

        System.out.println("SetUsername: response: " + responseString);
        user.setUserName(newUserName);
    }

    /**
     * Changes the Rapperstatus of an User in the Database
     *
     * @param user     the User
     * @param isRapper the new status
     */
    public static void setIsRapper(User user, boolean isRapper) throws  IOException {
        setSettings(user, user.getNotifications(), isRapper);
    }

    /**
     * Changes the Notificationstatus of an User in the Database
     *
     * @param user          the User
     * @param notifications the new status
     */
    public static void setNotifications(User user, boolean notifications) throws  IOException {
        setSettings(user, notifications, user.isRapper());
    }

    public static void setSettings(User user, boolean notifications, boolean isRapper) throws  IOException {
        String url = "/account/settings";
        Settings settings = new Settings(notifications,isRapper);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String requestString = gson.toJson(settings);

        String responseString = ConnectionController.postJSON(url, requestString);
        System.out.println(responseString);

        user.setIsRapper(isRapper);
        user.setNotifications(notifications);
    }

    /**
     * get Settings from an User
     *
     * @return returns Setting from the User, if the User doesn't exist it returns null
     */
    public static Settings getSettings() throws  IOException, MalformedURLException {
        String url = "/account/settings";

        String responseString = ConnectionController.getJSON(url);
        System.out.println("getSettings response: " + responseString);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(responseString, Settings.class);
    }

    /**
     * Uploads a new Profilepicture for the User
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */


    public static void setProfileInformation(User user, String location, String aboutMe) throws  IOException {
        String url = "/profile";

        ProfileInformationRequest request = new ProfileInformationRequest();
        request.setAbout_me(aboutMe);
        request.setCity(location);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String requestString = gson.toJson(request);

        String responseString =  ConnectionController.postJSON(url, requestString);
        System.out.println("setProfileInformation: response: " + responseString);

        //success = ConnectionController.sendJSON(url, profilInformation);

        user.setAboutMe(aboutMe);
        user.setLocation(location);
    }

    public static void setLocation(User user, String location) throws  IOException {
        UserController.setProfileInformation(user, location, user.getAboutMe());
    }

    public static void setAboutMe(User user, String aboutMe) throws IOException {
        UserController.setProfileInformation(user, user.getLocation(), aboutMe);
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
        rapper = new Rapper(user.getUserName(),prof.getStatistics().getWins(),prof.getStatistics().getDefeats());

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
    public static void changePassword(String oldPassword, String newPassword) throws IOException {
        String url = "/account/password";

        PasswordRequest request = new PasswordRequest();
        request.setPassword(newPassword);
        request.setOld_password(oldPassword);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String requestString = gson.toJson(request);

        String responseString = ConnectionController.postJSON(url, requestString);
        System.out.println("ChangePassword response: " + responseString);
    }
}
