package com.batllerap.hsosna.rapbattle16bars.Model.request;

import java.io.Serializable;

/**
 * Created by woors on 17.12.2015.
 */
public class UsernameRequest implements Serializable{
    private String username;

    public UsernameRequest(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
