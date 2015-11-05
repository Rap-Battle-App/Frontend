package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.User;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by woors on 05.11.2015.
 */
public class AuthentifactionControllerTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testLogin() throws Exception {
        User user;
        try {
            user = AuthentifactionController.login("testRapper", "abc123");
            Assert.assertNotNull("User is not null", user);
            Assert.assertTrue("Rapper is no Rapper", user.getIsRapper());
            Assert.assertTrue("Rapper has no Rapper", user.getRapper() != null);
        }
        catch(Exception e){
            Assert.fail("Exception thrown: "+e.getMessage());
        }
        try{

            user = AuthentifactionController.login("testViewer", "abc123");
            Assert.assertTrue("Viewer is a Rapper", !user.getIsRapper());
            Assert.assertTrue("Viewer has a Rapper", user.getRapper() == null);

            user = AuthentifactionController.login("testtesttest", "abc123");
            Assert.assertTrue("User is not null", user == null);
        }
        catch(Exception e){
            Assert.fail("Exception thrown: "+e.getMessage());
        }
    }

    @Test
    public void testRegister() throws Exception {

    }

    @Test
    public void testLogout() throws Exception {

    }

    @Test
    public void testResetPassword() throws Exception {

    }
}