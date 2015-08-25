package org.highway.whatsup.data.entity.koex;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by engeng on 8/22/15.
 */
@Root(name = "data")
public class KoExAccidentEntity {

    @Element(name = "type", required = false)
    String type;

    @Element(name = "incidenttype", required = false)
    String incidentType;

    @Element(name = "lanesblocktype", required = false)
    String lanesBlockType;

    @Element(name = "incidentmsg", required = false)
    String incidentMsg;

    @Element(name = "incidentduration", required = false)
    String incidentDuration;

    @Element(name = "expecteddetourmsg", required = false)
    String expectedDetourMsg;

    @Element(name = "eventdirection", required = false)
    String eventDirection;

    @Element(name = "expectedcnt", required = false)
    int expectedCnt;

    @Element(name = "coordx", required = false)
    double coordX;

    @Element(name = "coordy", required = false)
    double coordY;

    public String getType() {
        return type;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public String getLanesBlockType() {
        return lanesBlockType;
    }

    public String getIncidentMsg() {
        return incidentMsg;
    }

    public String getIncidentDuration() {
        return incidentDuration;
    }

    public String getExpectedDetourMsg() {
        return expectedDetourMsg;
    }

    public String getEventDirection() {
        return eventDirection;
    }

    public int getExpectedCnt() {
        return expectedCnt;
    }

    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }
}
