package com.batllerap.hsosna.rapbattle16bars.Model;

import java.io.Serializable;

/**
 * Created by woors on 03.11.2015.
 */
public class Settings implements Serializable{
    private boolean notifications;
    private boolean rapper;

    public Settings(){

    }

    public Settings(boolean notifications, boolean isRapper){
        this.setNotifications(notifications);
        this.setRapper(isRapper);
    }

    public boolean isNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public boolean isRapper() {
        return rapper;
    }

    public void setRapper(boolean rapper) {
        this.rapper = rapper;
    }
}
