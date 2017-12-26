package com.leeway.templapp.Bus;

import com.squareup.otto.Bus;

/**
 * Created by work on 7/24/2017.
 */

public class BusFactory {

    public static final Bus bus = new Bus();

    public static Bus getBus(){
        return bus;
    }
}
