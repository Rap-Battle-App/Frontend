package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.Settings;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;

import junit.framework.Assert;

import org.json.JSONException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by woors on 05.11.2015.
 */
public class UserControllerTest {

    private static User user;

    @BeforeClass
    public static void beforeClass() throws IOException, JSONException {
        AuthentificationController.logout();
        user  = AuthentificationController.login("bla123", "bla123");
    }

    @Test
    public void testSetUsername() throws Exception {
        Assert.assertTrue(UserController.setUsername(user,"newName"));
        Assert.assertTrue(user.getUserName().equals("newName"));
    }

    @Test
    public void testSetIsRapper() throws Exception {
        Assert.assertTrue(UserController.setIsRapper(user, false));
        Assert.assertTrue(!user.isRapper());
        Assert.assertTrue(UserController.setIsRapper(user,true));
        Assert.assertTrue(user.isRapper());
    }

    @Test
    public void testSetNotifications() throws Exception {
        Assert.assertTrue(UserController.setNotifications(user, false));
        Assert.assertTrue(!user.getNotifications());
        Assert.assertTrue(UserController.setNotifications(user, true));
        Assert.assertTrue(user.getNotifications());
    }

    @Test
    public void testSetSettings() throws Exception {
        Assert.assertTrue(UserController.setSettings(user, false, false));
        Assert.assertTrue(!user.getNotifications());
        Assert.assertTrue(!user.isRapper());
        Assert.assertTrue(UserController.setSettings(user, false, true));
        Assert.assertTrue(!user.getNotifications());
        Assert.assertTrue(user.isRapper());
        Assert.assertTrue(UserController.setSettings(user, true, false));
        Assert.assertTrue(user.getNotifications());
        Assert.assertTrue(!user.isRapper());
        Assert.assertTrue(UserController.setSettings(user, true, true));
        Assert.assertTrue(user.getNotifications());
        Assert.assertTrue(user.isRapper());
    }

    @Test
    public void testGetSettings() throws Exception {
        Settings set = UserController.getSettings();
    }

    @Test
    public void testSetProfilPicture() throws Exception {
        //Assert.assertTrue(UserController.setProfilPicture((byte) 5));
    }

    @Test
    public void testSetProfileInformation() throws Exception {
        Assert.assertTrue(UserController.setProfileInformation(user, "hier und da", "blablabbla"));
        Assert.assertTrue(user.getLocation().equals("hier und da"));
        Assert.assertTrue(user.getAboutMe().equals("blablabbla"));
    }

    @Test
    public void testGetUser() throws Exception {
        /*User user;
        try {
            user = AuthentificationController.login("testtesttest", "abc123");
            Assert.assertTrue("User is not null", user == null);


            user = AuthentificationController.login("testViewer", "abc123");
            Assert.assertTrue("Viewer is a Rapper", !user.isRapper());
            Assert.assertTrue("Viewer has a Rapper", user.getRapper() == null);

            user = AuthentificationController.login("testRapper", "abc123");
            Assert.assertNotNull("User is not null", user);
            Assert.assertTrue("Rapper is no Rapper", user.isRapper());
            Assert.assertTrue("Rapper has no Rapper", user.getRapper() != null);
        }
        catch(Exception e){
            Assert.fail("Exception thrown: "+e.getMessage());
        }*/
    }

    @Test
    public void testChangePassword(){
        try{
            Assert.assertTrue("Couldnt change the Password", UserController.changePassword("bla123", "bla1234"));
        }
        catch(Exception e){
            Assert.fail();
        }
    }
}