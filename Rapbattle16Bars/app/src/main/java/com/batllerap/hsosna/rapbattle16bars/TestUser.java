package com.batllerap.hsosna.rapbattle16bars;

/**
 * Created by Robert on 21.11.15.
 */

public class TestUser {
    String name, username, password;
    int age;

    public TestUser (String name, int age, String username, String password) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public TestUser (String username, String password){
        this.username = username;
        this.password = password;
        this.age = -1;
        this.name = "";
    }
}
