package org.highway.whatsup.bus;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class BusProvider {

    private static final Bus REST_BUS = new Bus(ThreadEnforcer.ANY);
    private static final MainThreadBus UI_BUS = new MainThreadBus();

    private BusProvider() {}

    public static Bus getRestBusInstance() {
        return REST_BUS;
    }

    public static MainThreadBus getUIBusInstance () {
        return UI_BUS;
    }
}
