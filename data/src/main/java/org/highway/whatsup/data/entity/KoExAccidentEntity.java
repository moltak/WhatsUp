package org.highway.whatsup.data.entity;

import org.simpleframework.xml.Root;

/**
 * Created by engeng on 8/22/15.
 */
@Root(name = "data")
public class KoExAccidentEntity {
    String type,IncidentType, LanesBlockType, IncidentMsg, IncidentDuration,
            ExpectedDetourMsg, EventDirection;
    int ExpectedCnt;
    double CoordX, CoordY;

    public String getType() {
        return type;
    }

    public String getIncidentType() {
        return IncidentType;
    }

    public String getLanesBlockType() {
        return LanesBlockType;
    }

    public String getIncidentMsg() {
        return IncidentMsg;
    }

    public String getIncidentDuration() {
        return IncidentDuration;
    }

    public String getExpectedDetourMsg() {
        return ExpectedDetourMsg;
    }

    public String getEventDirection() {
        return EventDirection;
    }

    public int getExpectedCnt() {
        return ExpectedCnt;
    }

    public double getCoordX() {
        return CoordX;
    }

    public double getCoordY() {
        return CoordY;
    }
}
