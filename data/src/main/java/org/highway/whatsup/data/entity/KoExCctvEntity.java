package org.highway.whatsup.data.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by engeng on 8/23/15.
 */
@Root(name = "data")
public class KoExCctvEntity {
    @Element(name = "CCTVType")
    String CCTVType;

    @Element(name = "FileCreateTime")
    String FileCreateTime;

    @Element(name = "CCTVFormat")
    String CCTVFormat;

    @Element(name = "RoadSectionId")
    String RoadSectionId;

    @Element(name = "CCTVName")
    String CCTVName;

    @Element(name = "CCTVurl")
    String CCTVurl;

    @Element(name = "CoordX")
    double CoordX;

    @Element(name = "CooddY")
    double CooddY;

    public String getCCTVType() {
        return CCTVType;
    }

    public String getFileCreateTime() {
        return FileCreateTime;
    }

    public String getCCTVFormat() {
        return CCTVFormat;
    }

    public String getRoadSectionId() {
        return RoadSectionId;
    }

    public String getCCTVName() {
        return CCTVName;
    }

    public String getCCTVurl() {
        return CCTVurl;
    }

    public double getCoordX() {
        return CoordX;
    }

    public double getCooddY() {
        return CooddY;
    }
}
