package com.batllerap.hsosna.rapbattle16bars;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Robert on 21.11.15.
 */

public class TestUserLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public TestUserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeTestUserData(TestUser testUser) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", testUser.name);
        spEditor.putInt("age", testUser.age);
        spEditor.putString("username", testUser.username);
        spEditor.putString("password", testUser.password);
        spEditor.commit();
    }

    public TestUser getLoggedInTestUser() {
        String name = userLocalDatabase.getString("name", "");
        int age = userLocalDatabase.getInt("age", -1);
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");

        TestUser storedUser = new TestUser(name, age, username, password);

        return storedUser;
    }

    public void setTestUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public void clearTestUserData() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
