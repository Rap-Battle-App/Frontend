package com.batllerap.hsosna.rapbattle16bars.Controller;

import com.batllerap.hsosna.rapbattle16bars.Model.Settings;
import com.batllerap.hsosna.rapbattle16bars.Model.User;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by woors on 05.11.2015.
 */
public class UserControllerTest {

    @Test
    public void testSetUsername() throws Exception {
        User user = new User("testUser", null, null, null, false, false, null);
        Assert.assertTrue(UserController.setUsername(user,"newName"));
        Assert.assertTrue(user.getUserName().equals("newName"));
    }

    @Test
    public void testSetIsRapper() throws Exception {
        User user = new User("testUser", null, null, null, false, false, null);
        Assert.assertTrue(UserController.setIsRapper(user, false));
        Assert.assertTrue(!user.getIsRapper());
        Assert.assertTrue(UserController.setIsRapper(user,true));
        Assert.assertTrue(user.getIsRapper());

        user = new User("testUser", null, null, null, false, true, null);
        Assert.assertTrue(UserController.setIsRapper(user, false));
        Assert.assertTrue(!user.getIsRapper());
        Assert.assertTrue(UserController.setIsRapper(user, true));
        Assert.assertTrue(user.getIsRapper());

        user = new User("testUser", null, null, null, true, false, null);
        Assert.assertTrue(UserController.setIsRapper(user, false));
        Assert.assertTrue(!user.getIsRapper());
        Assert.assertTrue(UserController.setIsRapper(user, true));
        Assert.assertTrue(user.getIsRapper());

        user = new User("testUser", null, null, null, true, true, null);
        Assert.assertTrue(UserController.setIsRapper(user, false));
        Assert.assertTrue(!user.getIsRapper());
        Assert.assertTrue(UserController.setIsRapper(user,true));
        Assert.assertTrue(user.getIsRapper());
    }

    @Test
    public void testSetNotifications() throws Exception {
        User user = new User("testUser", null, null, null, false, false, null);
        Assert.assertTrue(UserController.setNotifications(user,false));
        Assert.assertTrue(!user.getNotifications());
        Assert.assertTrue(UserController.setNotifications(user, true));
        Assert.assertTrue(user.getNotifications());

        user = new User("testUser", null, null, null, false, true, null);
        Assert.assertTrue(UserController.setNotifications(user, false));
        Assert.assertTrue(!user.getNotifications());
        Assert.assertTrue(UserController.setNotifications(user, true));
        Assert.assertTrue(user.getNotifications());

        user = new User("testUser", null, null, null, true, false, null);
        Assert.assertTrue(UserController.setNotifications(user, false));
        Assert.assertTrue(!user.getNotifications());
        Assert.assertTrue(UserController.setNotifications(user, true));
        Assert.assertTrue(user.getNotifications());

        user = new User("testUser", null, null, null, true, true, null);
        Assert.assertTrue(UserController.setNotifications(user, false));
        Assert.assertTrue(!user.getNotifications());
        Assert.assertTrue(UserController.setNotifications(user, true));
        Assert.assertTrue(user.getNotifications());
    }

    @Test
    public void testSetSettings() throws Exception {
        User user = new User("testUser", null, null, null, false, false, null);
        Assert.assertTrue(UserController.setSettings(user, false, false));
        Assert.assertTrue(!user.getNotifications());
        Assert.assertTrue(!user.getIsRapper());
        Assert.assertTrue(UserController.setSettings(user, false, true));
        Assert.assertTrue(!user.getNotifications());
        Assert.assertTrue(user.getIsRapper());
        Assert.assertTrue(UserController.setSettings(user, true, false));
        Assert.assertTrue(user.getNotifications());
        Assert.assertTrue(!user.getIsRapper());
        Assert.assertTrue(UserController.setSettings(user, true, true));
        Assert.assertTrue(user.getNotifications());
        Assert.assertTrue(user.getIsRapper());

        user = new User("testUser", null, null, null, false, true, null);
        Assert.assertTrue(UserController.setSettings(user, false, false));
        Assert.assertTrue(!user.getNotifications());
        Assert.assertTrue(!user.getIsRapper());
        Assert.assertTrue(UserController.setSettings(user, false, true));
        Assert.assertTrue(!user.getNotifications());
        Assert.assertTrue(user.getIsRapper());
        Assert.assertTrue(UserController.setSettings(user, true, false));
        Assert.assertTrue(user.getNotifications());
        Assert.assertTrue(!user.getIsRapper());
        Assert.assertTrue(UserController.setSettings(user, true, true));
        Assert.assertTrue(user.getNotifications());
        Assert.assertTrue(user.getIsRapper());

        user = new User("testUser", null, null, null, true, false, null);
        Assert.assertTrue(UserController.setSettings(user, false, false));
        Assert.assertTrue(!user.getNotifications());
        Assert.assertTrue(!user.getIsRapper());
        Assert.assertTrue(UserController.setSettings(user, false, true));
        Assert.assertTrue(!user.getNotifications());
        Assert.assertTrue(user.getIsRapper());
        Assert.assertTrue(UserController.setSettings(user, true, false));
        Assert.assertTrue(user.getNotifications());
        Assert.assertTrue(!user.getIsRapper());
        Assert.assertTrue(UserController.setSettings(user, true, true));
        Assert.assertTrue(user.getNotifications());
        Assert.assertTrue(user.getIsRapper());

        user = new User("testUser", null, null, null, true, true, null);
        Assert.assertTrue(UserController.setSettings(user, false, false));
        Assert.assertTrue(!user.getNotifications());
        Assert.assertTrue(!user.getIsRapper());
        Assert.assertTrue(UserController.setSettings(user, false, true));
        Assert.assertTrue(!user.getNotifications());
        Assert.assertTrue(user.getIsRapper());
        Assert.assertTrue(UserController.setSettings(user, true, false));
        Assert.assertTrue(user.getNotifications());
        Assert.assertTrue(!user.getIsRapper());
        Assert.assertTrue(UserController.setSettings(user, true, true));
        Assert.assertTrue(user.getNotifications());
        Assert.assertTrue(user.getIsRapper());
    }

    @Test
    public void testGetSettings() throws Exception {
        Settings set = UserController.getSettings("testBlabla");
        Assert.assertTrue(set == null);

        set = UserController.getSettings("testViewer");
        Assert.assertNotNull(set);
        Assert.assertTrue(!set.getIsRapper());
        Assert.assertTrue(set.getNotifications());

        set = UserController.getSettings("testRapper");
        Assert.assertNotNull(set);
        Assert.assertTrue(set.getIsRapper());
        Assert.assertTrue(set.getNotifications());
    }

    @Test
    public void testSetProfilPicture() throws Exception {
        Assert.assertTrue(UserController.setProfilPicture((byte) 5));
    }

    @Test
    public void testSetProfileInformation() throws Exception {
        User user = new User("testUser", null, null, null, false, true, null);
        Assert.assertTrue(UserController.setProfileInformation(user, "hier und da", "blablabbla"));
        Assert.assertTrue(user.getLocation().equals("hier und da"));
        Assert.assertTrue(user.getAboutMe().equals("blablabbla"));

        User user2 = new User("testUser2", "location", "about", null, false, true, null);
        Assert.assertTrue(UserController.setProfileInformation(user2, "hier und da", "blablabbla"));
        Assert.assertTrue(user2.getLocation().equals("hier und da"));
        Assert.assertTrue(user2.getAboutMe().equals("blablabbla"));
    }

    @Test
    public void testGetUser() throws Exception {
        User user;
        try {
            user = AuthentifactionController.login("testtesttest", "abc123");
            Assert.assertTrue("User is not null", user == null);


            user = AuthentifactionController.login("testViewer", "abc123");
            Assert.assertTrue("Viewer is a Rapper", !user.getIsRapper());
            Assert.assertTrue("Viewer has a Rapper", user.getRapper() == null);

            user = AuthentifactionController.login("testRapper", "abc123");
            Assert.assertNotNull("User is not null", user);
            Assert.assertTrue("Rapper is no Rapper", user.getIsRapper());
            Assert.assertTrue("Rapper has no Rapper", user.getRapper() != null);
        }
        catch(Exception e){
            Assert.fail("Exception thrown: "+e.getMessage());
        }
    }
}