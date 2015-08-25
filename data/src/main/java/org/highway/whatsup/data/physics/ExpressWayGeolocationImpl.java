package org.highway.whatsup.data.physics;

import android.location.Location;

import javax.inject.Inject;

/**
 * Created by engeng on 8/24/15.
 * todo: mock 데이터를 들고있음.
 */
public class ExpressWayGeolocationImpl implements ExpressWayGeolocation {

    private Location location;
    private String nodeName;
    private boolean upLine;
    private int posOnNode;

    @Inject public ExpressWayGeolocationImpl() {}

    @Override
    public String nodeName() {
        return nodeName;
    }

    @Override
    public boolean upLine() {
        return upLine;
    }

    @Override
    public double posOnNode() {
        return posOnNode;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public void figureOutGeolocation() throws IllegalStateException {
        if (location == null) {
            throw new IllegalStateException("Location field cannot be null. It must be set.");
        }

        // processing
        nodeName = "중앙고속도로";
        upLine = true;
        posOnNode = 15;

        location = null;
    }
}
