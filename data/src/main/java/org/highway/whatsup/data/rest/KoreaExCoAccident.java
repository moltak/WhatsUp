package org.highway.whatsup.data.rest;

import org.highway.whatsup.data.entity.KoExAccidentEntity;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by engeng on 8/22/15.
 */
public class KoreaExCoAccident {

    public interface Request {
        @GET("/NIncidentIdentity")
        Observable<Response> get(@QueryMap Map<String, Object> map);
    }

    public static class Params {
        private final String key, reqType, type;
        private final double minX, minY, maxX, maxY;

        public Params(String key, String reqType, String type,
                      double minX, double minY, double maxX, double maxY) {
            this.key = key;
            this.reqType = reqType;
            this.type = type;
            this.minX = minX;
            this.minY = minY;
            this.maxX = maxX;
            this.maxY = maxY;
        }

        public Map<String, Object> toMap() {
            Map<String, Object> map = new HashMap<>();
            map.put("key", key);
            map.put("ReqType", reqType);
            map.put("type", type);
            map.put("MinX", minX);
            map.put("MinY", minY);
            map.put("MaxX", maxX);
            map.put("MaxY", maxY);
            return map;
        }
    }

    @Root(name = "response")
    public static class Response {

        @Element(name = "rs", type = String.class)
        String rs;

        @Element(name = "CoordType")
        String coordType;

        @Element(name = "DataCount")
        int dataCount;

        @ElementList(name = "data")
        List<KoExAccidentEntity> data;

        public String getCoordType() {
            return coordType;
        }

        public int getDataCount() {
            return dataCount;
        }

        public List<KoExAccidentEntity> getData() {
            return data;
        }

        public String getRs() {
            return rs;
        }
    }
}
