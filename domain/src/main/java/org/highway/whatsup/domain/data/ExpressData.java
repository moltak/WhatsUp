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
    private double lat, lng;
    private SpeedMeter.Progression progressionSpeed;
    private String LANE_BLOCK_TYPE[] = {
            "통제없음", "갓길통제", "차로부분통제", "전면통제"};
    private String laneBlock = LANE_BLOCK_TYPE[0];

    public ExpressData(float speed, double lat, double lng, SpeedMeter.Progression progressionSpeed,
                       String cctvUrl, String msg, int laneBlockType) {
        this.cctvUrl = cctvUrl;
        this.msg = msg;
        this.speed = speed;
        this.lat = lat;
        this.lng = lng;
        this.progressionSpeed = progressionSpeed;
        try {
            this.laneBlock = LANE_BLOCK_TYPE[laneBlockType];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.laneBlock = LANE_BLOCK_TYPE[0];
        }
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

    public String getLaneBlock() {
        return laneBlock;
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
        dest.writeInt(this.progressionSpeed == null ? -1 : this.progressionSpeed.ordinal());
        dest.writeStringArray(this.LANE_BLOCK_TYPE);
        dest.writeString(this.laneBlock);
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
        int tmpProgressionSpeed = in.readInt();
        this.progressionSpeed = tmpProgressionSpeed == -1 ? null : SpeedMeter.Progression.values()[tmpProgressionSpeed];
        this.LANE_BLOCK_TYPE = in.createStringArray();
        this.laneBlock = in.readString();
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
