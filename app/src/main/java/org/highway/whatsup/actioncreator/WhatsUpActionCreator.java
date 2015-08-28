package org.highway.whatsup.actioncreator;

import android.content.Context;
import android.location.Location;

import org.highway.whatsup.data.entity.whatsup.WhatsupResultEntity;
import org.highway.whatsup.data.physics.DefaultExpressWayGeolocation;
import org.highway.whatsup.data.physics.ExpressWayGeolocation;
import org.highway.whatsup.data.physics.SpeedMeter;
import org.highway.whatsup.data.rest.whatsup.WhatsUpApiProvider;
import org.highway.whatsup.data.rest.whatsup.functions.WhatsupApi;
import org.highway.whatsup.data.uuid.HashedUuidGenerator;
import org.highway.whatsup.domain.actioncreator.DefaultActionCreator;
import org.highway.whatsup.domain.data.ExpressData;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

/**
 * Created by engeng on 8/23/15.
 */
public class WhatsUpActionCreator {
    final WhatsUpApiProvider whatsUpApiProvider;
    final DefaultActionCreator defaultActionCreator;
    final ExpressWayGeolocation expressWayGeolocation;
    final String uuid;
    Behavior behavior;

    public enum Behavior {
        PRINT, NOTHING
    }

    @Inject public WhatsUpActionCreator(Context context,
                                        WhatsUpApiProvider whatsUpApiProvider,
                                        DefaultActionCreator defaultActionCreator,
                                        DefaultExpressWayGeolocation expressWayGeolocation) {
        this.whatsUpApiProvider = whatsUpApiProvider;
        this.defaultActionCreator = defaultActionCreator;
        this.expressWayGeolocation = expressWayGeolocation;
        this.uuid = HashedUuidGenerator.gen(context);
        this.behavior = Behavior.NOTHING;
    }

    /**
     * @param speed m/s -> speed 가 3이상이면 HIGH_PROGRESSION
     */
    public ExpressData doit(Location location, float speed, double lat, double lng)
            throws ExecutionException, InterruptedException {
        behavior = Behavior.NOTHING;
        ExpressData data = retrieveWhatsUpData(location, speed, lat, lng);
        if(data.getMsg() == null) { // 서버에 메시지가 없음. -> 속도 체크해서 한국도로공사에서 정보를 가져와야함
            data = defaultActionCreator.doit(speed, lat, lng);
            if (data.getProgressionSpeed() == SpeedMeter.Progression.LOW_SPEED) { // 속도가 느리면 화면 출력.
                behavior = Behavior.PRINT;
            }
            sendExpressProgressionData(data, location);
        }
        return data;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    /**
     * whatsup 서버에서 고속도로 교통정보를 가져옴.
     * @param location
     * @param speed
     * @param lat
     * @param lng
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private ExpressData retrieveWhatsUpData(Location location, float speed, double lat, double lng)
            throws ExecutionException, InterruptedException {
        expressWayGeolocation.setLocation(location);
        expressWayGeolocation.figureOutGeolocation();
        WhatsupApi.Params params = new WhatsupApi.Params(
                uuid,
                expressWayGeolocation.nodeName(),
                expressWayGeolocation.upLine(),
                expressWayGeolocation.posOnNode(),
                lat,
                lng,
                speed,
                0, // 순간 가속도를 어떻게 알아내는지 잘 모름.
                location.getBearing());
        WhatsupResultEntity r = whatsUpApiProvider.getWhatsupApi(params).toBlocking().toFuture().get();
        String msg = null;
        if (r.getCode() == 1002) {
            msg = r.getMsg();
        }
        ExpressData data =  new ExpressData(speed, lat, lng, defaultActionCreator.getProgression(speed));
        data.setMsg(msg);
        return data;
    }

    /**
     * whatsup 서버에 현재 운행 정보 전송.
     * @param data
     * @param location
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private void sendExpressProgressionData(ExpressData data, Location location)
            throws ExecutionException, InterruptedException {
        expressWayGeolocation.setLocation(location);
        expressWayGeolocation.figureOutGeolocation();
        WhatsupApi.Params params = new WhatsupApi.Params(
                uuid,
                expressWayGeolocation.nodeName(),
                expressWayGeolocation.upLine(),
                expressWayGeolocation.posOnNode(),
                data.getLat(),
                data.getLng(),
                data.getSpeed(),
                0, // 순간 가속도를 어떻게 알아내는지 잘 모름.
                location.getBearing());
        WhatsupResultEntity r = whatsUpApiProvider.getStatics(params).toBlocking().toFuture().get();
        // return 체크 안함.
    }
}
