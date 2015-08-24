package org.highway.whatsup.data.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by engeng on 8/23/15.
 */
@Root(name = "data")
public class KoExCctvEntity {
    @Element(name = "cctvtype", type = Integer.class, required = false)
    Integer cctvType;

    @Element(name = "filecreatetime", required = false)
    String fileCreateTime;

    @Element(name = "cctvformat", required = false)
    String cctvFormat;

    @Element(name = "roadsectionid", required = false)
    String roadSectionId;

    @Element(name = "cctvname", required = false)
    String cctvName;

    @Element(name = "cctvurl", required = false)
    String cctvUrl;

    @Element(name = "coordx", type = Double.class, required = false)
    Double coordX;

    @Element(name = "coordy", type = Double.class, required = false)
    Double coordY;

    @Element(name = "cctvresolution", required = false)
    String cctvResolution;

    public String getFileCreateTime() {
        return fileCreateTime;
    }

    public Integer getCctvType() {
        return cctvType;
    }

    public String getCctvFormat() {
        return cctvFormat;
    }

    public String getRoadSectionId() {
        return roadSectionId;
    }

    public String getCctvName() {
        return cctvName;
    }

    public String getCctvUrl() {
        return cctvUrl;
    }

    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }
}
