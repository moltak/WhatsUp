package org.highway.whatsup.data.physics;

import android.location.Location;

/**
 * Created by engeng on 8/24/15.
 * todo: mock 데이터를 들고있음.
 */
public class fakeGeolocationForExpressWay {

    private final Location location;

    public fakeGeolocationForExpressWay(Location location) {
        this.location = location;
    }

    public String nodeName() {
        return "중앙고속도로";
    }

    public boolean upLine() {
        return true;
    }

    public double posOnNode() {
        return 0f;
    }
}
