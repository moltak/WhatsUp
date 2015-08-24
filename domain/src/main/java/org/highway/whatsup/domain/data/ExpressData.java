package org.highway.whatsup.domain.data;

import org.highway.whatsup.data.physics.SpeedMeter;

/**
 * Created by engeng on 8/23/15.
 */
public class ExpressData {

    private String cctvUrl, msg;
    private float speed;
    private double lat, lng;
    private SpeedMeter.Progression progressionSpeed;

    public ExpressData(float speed, double lat, double lng, SpeedMeter.Progression progressionSpeed,
                       String cctvUrl, String msg) {
        this.cctvUrl = cctvUrl;
        this.msg = msg;
        this.speed = speed;
        this.lat = lat;
        this.lng = lng;
        this.progressionSpeed = progressionSpeed;
    }

    public String getCctvUrl() {
        return cctvUrl;
    }

    public String getMsg() {
        return msg;
    }

    public float getSpeed() {
        return speed;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public SpeedMeter.Progression getProgressionSpeed() {
        return progressionSpeed;
    }

    /**
     * Below data for next version.
     * When we can know the position to relative on express way.
     */
    private String expressWayName, direction;
    private int expressWayId;
    private float relativePosition;

    public String getExpressWayName() {
        return expressWayName;
    }

    public void setExpressWayName(String expressWayName) {
        this.expressWayName = expressWayName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getExpressWayId() {
        return expressWayId;
    }

    public void setExpressWayId(int expressWayId) {
        this.expressWayId = expressWayId;
    }

    public float getRelativePosition() {
        return relativePosition;
    }

    public void setRelativePosition(float relativePosition) {
        this.relativePosition = relativePosition;
    }
}
