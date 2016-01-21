package com.batllerap.hsosna.rapbattle16bars;

import com.squareup.otto.Bus;

/**
 * Created by Albert on 21.01.2016.
 */
public class MyBus {
    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }
}
