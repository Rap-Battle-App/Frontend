package com.batllerap.hsosna.rapbattle16bars.Model.Battle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by woors on 06.01.2016.
 */
public class RequestModell {
    private Request request;
    private java.util.Date date;

    public RequestModell(Request request) throws ParseException {
        this.setRequest(request);
        String dateString = request.getDate().getDate();
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"). parse(dateString);
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }
}
