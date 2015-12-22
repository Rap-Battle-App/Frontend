package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by woors on 16.12.2015.
 */
public class ConnectionControllerTest {

    @Test
    public void testRegister() throws Exception {
        /*User u = new User(1,"testuser","hier", "so", null,false,false,null);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(u));

        User user = AuthentificationController.register("testUser123", "testUser123@bla.de",  "bla123");
        System.out.println(user);
        */

    }

    @Test
    public void testLogin() throws Exception {
        AuthentificationController.logout("bla123");
        User user = AuthentificationController.login("bla123", "bla123");
        System.out.println("ERgebnis: " +user);
        //UserController.getSettings();
    }
}