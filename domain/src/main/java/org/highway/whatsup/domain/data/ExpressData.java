package org.highway.whatsup.domain.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.highway.whatsup.data.physics.SpeedMeter;

/**
 * Created by engeng on 8/23/15.
 */
public class ExpressData implements Parcelable {

    private String cctvUrl, msg;
    private float speed;
    private double lat, lng, occuredLat, occuredLng;
    private SpeedMeter.Progression progressionSpeed;
    private String LANE_BLOCK_TYPE[] = {
            "통제없음", "갓길통제", "차로부분통제", "전면통제"};
    private String laneBlock = LANE_BLOCK_TYPE[0];
    private String eventDirection;
    private Type type;

    public enum Type {
        CONSTRUCTION, NONE, ACCIDENT
    }

    public ExpressData(float speed, double lat, double lng, SpeedMeter.Progression progressionSpeed) {
        this.speed = speed;
        this.lat = this.occuredLat = lat; // 상대 거리 보여줄때 사건지점과의 거리를 0으로 보여주기 위해.
        this.lng = this.occuredLng = lng;
        this.progressionSpeed = progressionSpeed;

        this.laneBlock = LANE_BLOCK_TYPE[0];
        this.eventDirection = "";
        this.type = Type.NONE;
        this.cctvUrl = "";
    }

    public ExpressData(float speed, double lat, double lng, double occuredLat, double occuredLng,
                       SpeedMeter.Progression progressionSpeed, String cctvUrl, String msg,
                       String eventDirection, int laneBlockType, ExpressData.Type type) {
        this.cctvUrl = cctvUrl;
        this.msg = msg;
        this.speed = speed;
        this.lat = lat;
        this.lng = lng;
        this.occuredLat = occuredLat;
        this.occuredLng = occuredLng;
        this.progressionSpeed = progressionSpeed;
        try {
            this.laneBlock = LANE_BLOCK_TYPE[laneBlockType];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.laneBlock = LANE_BLOCK_TYPE[0];
        }

        this.eventDirection = eventDirection;
        this.type = type;
    }

    public String getCctvUrl() {
        return cctvUrl;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public String getLaneBlock() {
        return laneBlock;
    }

    public double getOccuredLat() {
        return occuredLat;
    }

    public double getOccuredLng() {
        return occuredLng;
    }

    public String getEventDirection() {
        return eventDirection;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Msg: %s, cctvUrl: %s, speed: %3.2f, block: %s", msg, cctvUrl, speed, laneBlock);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cctvUrl);
        dest.writeString(this.msg);
        dest.writeFloat(this.speed);
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lng);
        dest.writeDouble(this.occuredLat);
        dest.writeDouble(this.occuredLng);
        dest.writeInt(this.progressionSpeed == null ? -1 : this.progressionSpeed.ordinal());
        dest.writeStringArray(this.LANE_BLOCK_TYPE);
        dest.writeString(this.laneBlock);
        dest.writeString(this.eventDirection);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeString(this.expressWayName);
        dest.writeString(this.direction);
        dest.writeInt(this.expressWayId);
        dest.writeFloat(this.relativePosition);
    }

    protected ExpressData(Parcel in) {
        this.cctvUrl = in.readString();
        this.msg = in.readString();
        this.speed = in.readFloat();
        this.lat = in.readDouble();
        this.lng = in.readDouble();
        this.occuredLat = in.readDouble();
        this.occuredLng = in.readDouble();
        int tmpProgressionSpeed = in.readInt();
        this.progressionSpeed = tmpProgressionSpeed == -1 ? null : SpeedMeter.Progression.values()[tmpProgressionSpeed];
        this.LANE_BLOCK_TYPE = in.createStringArray();
        this.laneBlock = in.readString();
        this.eventDirection = in.readString();
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : Type.values()[tmpType];
        this.expressWayName = in.readString();
        this.direction = in.readString();
        this.expressWayId = in.readInt();
        this.relativePosition = in.readFloat();
    }

    public static final Creator<ExpressData> CREATOR = new Creator<ExpressData>() {
        public ExpressData createFromParcel(Parcel source) {
            return new ExpressData(source);
        }

        public ExpressData[] newArray(int size) {
            return new ExpressData[size];
        }
    };
}
