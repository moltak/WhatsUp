package org.highway.whatsup.domain.data;

/**
 * Created by engeng on 8/23/15.
 */
public class ExpressData {

    private final String cctvUrl, msg;

    public ExpressData(String cctvUrl, String msg) {
        this.cctvUrl = cctvUrl;
        this.msg = msg;
    }

    public String getCctvUrl() {
        return cctvUrl;
    }

    public String getMsg() {
        return msg;
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
