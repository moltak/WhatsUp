package org.highway.whatsup.data.physics;

import android.location.Location;

/**
 * Created by engeng on 8/25/15.
 */
public interface ExpressWayGeolocation {
    String nodeName();
    boolean upLine();
    double posOnNode();
    void setLocation(Location location);
    void figureOutGeolocation() throws IllegalStateException;
}
