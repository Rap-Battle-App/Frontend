package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

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
    public void testLogin() throws Exception {
        User user = AuthentificationController.login("bla123","bla1234");
    }
}