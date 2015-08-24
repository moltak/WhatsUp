package org.highway.whatsup.data.rest.koex.functions;

import org.highway.whatsup.data.entity.KoExCctvEntity;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by engeng on 8/23/15.
 */
public class KoExCctvApi {

    public interface Request {
        @GET("/NCCTVInfo")
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

    @Root(name = "response")
    public static class Response {
        @Element(name = "coordtype", required = false)
        private String coordType;

        @Element(name = "datacount", required = false)
        private int dataCount;

        @ElementList(name = "data", inline = true, required = false)
        private List<KoExCctvEntity> datas;

        public String getCoordType() {
            return coordType;
        }

        public int getDataCount() {
            return dataCount;
        }

        public List<KoExCctvEntity> getDatas() {
            return datas;
        }
    }
}
