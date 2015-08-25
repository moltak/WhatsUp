package org.highway.whatsup.actioncreator;

import android.location.Location;

import org.highway.whatsup.data.entity.whatsup.WhatsupResultEntity;
import org.highway.whatsup.data.mobileinfo.fakeUuidGenerator;
import org.highway.whatsup.data.physics.ExpressWayGeolocation;
import org.highway.whatsup.data.physics.ExpressWayGeolocationImpl;
import org.highway.whatsup.data.physics.SpeedMeter;
import org.highway.whatsup.data.rest.whatsup.WhatsUpApiProvider;
import org.highway.whatsup.data.rest.whatsup.functions.WhatsupApi;
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

    @Inject public WhatsUpActionCreator(WhatsUpApiProvider whatsUpApiProvider,
                                        DefaultActionCreator defaultActionCreator,
                                        ExpressWayGeolocationImpl expressWayGeolocation) {
        this.whatsUpApiProvider = whatsUpApiProvider;
        this.defaultActionCreator = defaultActionCreator;
        this.expressWayGeolocation = expressWayGeolocation;
    }

    public DefaultActionCreator getDefaultActionCreator() {
        return defaultActionCreator;
    }

    public WhatsUpApiProvider getWhatsUpApiProvider() {
        return whatsUpApiProvider;
    }

    public ExpressData doit(Location location, float speed, double lat, double lng)
            throws ExecutionException, InterruptedException {
        ExpressData data = retrieveWhatsUpData(location, speed, lat, lng);
        if(data.getMsg() == null) { // 서버에 메시지가 없음. -> 속도 체크해서 한국도로공사에서 정보를 가져와야함
            data = defaultActionCreator.doit(speed, lat, lng);
            if (data.getProgressionSpeed() == SpeedMeter.Progression.LOW_SPEED) { // 속도가 느리면 화면 출력.
                // 화면 출력
            }
            sendExpressProgressionData(data, location);
        }
        return data;
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
        WhatsupApi.Params params = new WhatsupApi.Params(
                fakeUuidGenerator.gen(null),
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
        return new ExpressData(speed, lat, lng, defaultActionCreator.getProgression(speed), null, msg);
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
        WhatsupApi.Params params = new WhatsupApi.Params(
                fakeUuidGenerator.gen(null),
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
