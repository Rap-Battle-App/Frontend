package com.batllerap.hsosna.rapbattle16bars.Model;

/**
 * Created by woors on 03.11.2015.
 */
public class Settings {
    private boolean notifications;
    private boolean isRapper;

    public boolean getNotifications(){
        return this.notifications;
    }

    public boolean getIsRapper(){
        return this.isRapper;
    }

    public Settings(boolean notifications, boolean isRapper){
        this.notifications = notifications;
        this.isRapper = isRapper;
    }
}
