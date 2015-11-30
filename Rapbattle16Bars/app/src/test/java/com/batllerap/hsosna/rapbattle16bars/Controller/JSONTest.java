package com.batllerap.hsosna.rapbattle16bars.Controller;

/**
 * Created by woors on 05.11.2015.
 */

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
public class JSONTest {
    @Test
    public void testTest() throws Exception {
        JSONObject object = null;

        try {
            object = new JSONObject("{\"test\":\"bla\"}");
            System.out.println(object.getString("test"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
