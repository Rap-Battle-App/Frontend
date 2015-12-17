package com.batllerap.hsosna.rapbattle16bars.Model.response;

import java.io.Serializable;

/**
 * Created by woors on 17.12.2015.
 */
public class LoginResponse implements Serializable {
    private int user_id;

    public LoginResponse(){

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
