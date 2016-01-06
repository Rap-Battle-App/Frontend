package com.batllerap.hsosna.rapbattle16bars.Model.Battle;

import java.io.Serializable;

/**
 * Created by woors on 06.01.2016.
 */
public class Date implements Serializable{
    private String date;
    private int timezone_type;
    private String timezone;

    public Date(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTimezone_type() {
        return timezone_type;
    }

    public void setTimezone_type(int timezone_type) {
        this.timezone_type = timezone_type;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
