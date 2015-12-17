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

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRegister() throws Exception {
        /*User u = new User(1,"testuser","hier", "so", null,false,false,null);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(u));

        User user = AuthentificationController.register("tesfsarradaasfsfsatUssdflkjn", "aslkdrwafvare@bla.de",  "bla123");
        System.out.println(user);

    */
    }

    @Test
    public void testLogin() throws Exception {
        User user = AuthentificationController.login("tesfsarradaasfsfsatUssdflkjn", "bla123");
        System.out.println("ERgebnis: " +user);
    }
}