package org.highway.whatsup.data.entity;

import org.simpleframework.xml.Root;

/**
 * Created by engeng on 8/23/15.
 */
@Root(name = "data")
public class KoExEventEntity {
    String type, eventId, EventType, LanesBlockType, LanesBlocked, EventStartDay, EventEndDay,
            EventStartTime, EventEndTime, EventStatusMsg, ExpectedDetourMsg, EventDirection;
    double CoordX, CoordY;
    int ExpectedCnt;

    public String getType() {
        return type;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return EventType;
    }

    public String getLanesBlockType() {
        return LanesBlockType;
    }

    public String getLanesBlocked() {
        return LanesBlocked;
    }

    public String getEventStartDay() {
        return EventStartDay;
    }

    public String getEventEndDay() {
        return EventEndDay;
    }

    public String getEventStartTime() {
        return EventStartTime;
    }

    public String getEventEndTime() {
        return EventEndTime;
    }

    public String getEventStatusMsg() {
        return EventStatusMsg;
    }

    public String getExpectedDetourMsg() {
        return ExpectedDetourMsg;
    }

    public String getEventDirection() {
        return EventDirection;
    }

    public double getCoordX() {
        return CoordX;
    }

    public double getCoordY() {
        return CoordY;
    }

    public int getExpectedCnt() {
        return ExpectedCnt;
    }
}
