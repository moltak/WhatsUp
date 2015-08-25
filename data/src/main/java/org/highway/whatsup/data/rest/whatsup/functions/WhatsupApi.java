package org.highway.whatsup.data.rest.whatsup.functions;

import org.highway.whatsup.data.entity.whatsup.WhatsupResultEntity;

import java.util.HashMap;
import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by engeng on 8/24/15.
 */
public class WhatsupApi {

    public interface Request {
        @GET("/whatsup/api.php?mode=whatsup")
        Observable<WhatsupResultEntity> whatsup(@QueryMap Map<String, Object> params);

        @GET("/whatsup/api.php?mode=static")
        Observable<WhatsupResultEntity> statics(@QueryMap Map<String, Object> params);
    }

    public static class Params {
        final String uuid, nodeName;
        final boolean upLine;
        final double posOnNode, lat, lon, speed, acceleration, orientation;

        public Params(String uuid, String nodeName, boolean upLine, double posOnNode, double lat,
                      double lon, double speed, double acceleration, double orientation) {
            this.uuid = uuid;
            this.nodeName = nodeName;
            this.upLine = upLine;
            this.posOnNode = posOnNode;
            this.lat = lat;
            this.lon = lon;
            this.speed = speed;
            this.acceleration = acceleration;
            this.orientation = orientation;
        }

        public Map<String, Object> toMap() {
            Map<String, Object> map = new HashMap<>();
            map.put("uuid", uuid);
            map.put("nodeName", nodeName);
            map.put("upLine", upLine);
            map.put("posOnNode", posOnNode);
            map.put("lat", lat);
            map.put("lon", lon);
            map.put("speed", speed);
            map.put("acceleration", acceleration);
            map.put("orientation", orientation);
            return map;
        }
    }
}
