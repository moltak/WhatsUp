package org.highway.whatsup.data.rest.koex.functions;

import org.highway.whatsup.data.entity.KoExEventEntity;
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
public class KoExEventApi {

    public interface Request {
        @GET("/NEventIdentity")
        Observable<Response> get(@QueryMap Map<String, Object> map);
    }

    public static class Params {
        private final String key, ReqType, type;
        private final double MinX, MinY, MaxX, MaxY;

        public Params(String key, String reqType, String type,
                      double minX, double minY, double maxX, double maxY) {
            this.key = key;
            ReqType = reqType;
            this.type = type;
            MinX = minX;
            MinY = minY;
            MaxX = maxX;
            MaxY = maxY;
        }

        public Map<String, Object> toMap() {
            Map<String, Object> map = new HashMap<>();
            map.put("key", key);
            map.put("ReqType", ReqType);
            map.put("type", type);
            map.put("MinX", MinX);
            map.put("MinY", MinY);
            map.put("MaxX", MaxX);
            map.put("MaxY", MaxY);
            return map;
        }
    }

    public static class Response {
        @Element(name = "coordtype", type = String.class, required = false)
        String CoordType;

        @Element(name = "datacount", type = Integer.class, required = false)
        int DataCount;

        @ElementList(name = "data", inline = true, required = false)
        List<KoExEventEntity> data;

        @Element(name = "response", required = false, type = String.class)
        String Response;

        public String getCoordType() {
            return CoordType;
        }

        public int getDataCount() {
            return DataCount;
        }

        public List<KoExEventEntity> getData() {
            return data;
        }

        public String getResponse() {
            return Response;
        }
    }
}
