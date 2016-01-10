package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Controller.ConnectionController;
import com.batllerap.hsosna.rapbattle16bars.Controller.UserController;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Date;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Request;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.RequestList;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.RequestModell;
import com.batllerap.hsosna.rapbattle16bars.Model.ProfilePreview;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import junit.framework.Assert;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Exception;

/**
 * Created by woors on 16.12.2015.
 */
public class ConnectionControllerTest {


    @Test
    public void testRegister() throws Exception {
        /*AuthentificationController.logout("bla123");
        User user = AuthentificationController.register("testUser123undsoweiter", "testUser123undsoweiter@bla.de",  "bla123");
        System.out.println("Ergebnis: " + user);*/
    }

    @Test
    public void testRequest() throws Exception {
        /*RequestList rl = new RequestList();
        rl.setOpponent_requests(new Request[0]);
        Request[] requests = new Request[1];
        ProfilePreview opponent = new ProfilePreview();
        opponent.setUser_id(36);
        opponent.setUsername("wRowe");
        opponent.setProfile_picture(null);
        Date date = new Date();
        date.setDate("2016-01-06 19:52:44");
        date.setTimezone("UTC");
        date.setTimezone_type(3);

        requests[0] = new Request(11,opponent, date);
        rl.setRequests(requests);

        RequestModell modell = new RequestModell(requests[0]);
        Assert.assertTrue("Nix Date", modell.getDate().toString() == date.getDate());*/
    }

    @Test
    public void testSendPicture() throws Exception{
        AuthentificationController.logout();
        User user = AuthentificationController.login("testUser123undsoweiter2", "bla123");
        File file = new File("D:/Truve.png");
        System.out.println("Datei gefunden: "+ file.getAbsolutePath() + " laenge: " + file.length());
        InputStream stream = new FileInputStream(file);
        System.out.println("Testergebnis \"testSendData\": " + UserController.setProfilPicture(stream, "png"));
    }

    @Test
    public void testSendVideo() throws Exception{
        AuthentificationController.logout();
        User user = AuthentificationController.login("testUser123undsoweiter2", "bla123");
        File file = new File("D:/Dingel.mp4");
        System.out.println("Datei gefunden: "+ file.getAbsolutePath() + " laenge: " + file.length());
        BattleController.uploadRound(0, 0, "mp4", file);
    }
}