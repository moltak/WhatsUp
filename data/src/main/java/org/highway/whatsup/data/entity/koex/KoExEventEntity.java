package org.highway.whatsup.data.entity.koex;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by engeng on 8/23/15.
 */
@Root(name = "data")
public class KoExEventEntity {

    @Element(name = "type", required = false)
    String type;

    @Element(name = "eventid", required = false)
    String eventId;

    @Element(name = "eventtype", required = false)
    String eventType;

    @Element(name = "lanesblocktype", required = false)
    String lanesBlockType;

    @Element(name = "lanesblocked", required = false)
    String lanesBlocked;

    @Element(name = "eventstartday", required = false)
    String eventStartDay;

    @Element(name = "eventendday", required = false)
    String eventEndDay;

    @Element(name = "eventstarttime", required = false)
    String eventStartTime;

    @Element(name = "eventendtime", required = false)
    String eventEndTime;

    @Element(name = "eventstatusmsg", required = false)
    String eventStatusMsg;

    @Element(name = "expecteddetourmsg", required = false)
    String expectedDetourMsg;

    @Element(name = "eventdirection", required = false)
    String eventDirection;

    @Element(name = "coordx", required = false)
    double coordX;

    @Element(name = "coordy", required = false)
    double coordY;

    @Element(name = "expectedcnt", required = false)
    int expectedCnt;

    public String getType() {
        return type;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getLanesBlockType() {
        return lanesBlockType;
    }

    public String getLanesBlocked() {
        return lanesBlocked;
    }

    public String getEventStartDay() {
        return eventStartDay;
    }

    public String getEventEndDay() {
        return eventEndDay;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public String getEventStatusMsg() {
        return eventStatusMsg;
    }

    public String getExpectedDetourMsg() {
        return expectedDetourMsg;
    }

    public String getEventDirection() {
        return eventDirection;
    }

    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public int getExpectedCnt() {
        return expectedCnt;
    }
}
