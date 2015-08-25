package org.highway.whatsup.actioncreator;

import android.location.Location;

import org.highway.whatsup.data.entity.whatsup.WhatsupResultEntity;
import org.highway.whatsup.data.mobileinfo.fakeUuidGenerator;
import org.highway.whatsup.data.physics.fakeGeolocationForExpressWay;
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

    @Inject public WhatsUpActionCreator(WhatsUpApiProvider whatsUpApiProvider,
                                        DefaultActionCreator defaultActionCreator) {
        this.whatsUpApiProvider = whatsUpApiProvider;
        this.defaultActionCreator = defaultActionCreator;
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
            sendHighSpeedProgressionData(data, location);
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
        fakeGeolocationForExpressWay fakeGeolocationForExpressWay = new fakeGeolocationForExpressWay(location);
        WhatsupApi.Params params = new WhatsupApi.Params(
                fakeUuidGenerator.gen(null),
                fakeGeolocationForExpressWay.nodeName(),
                fakeGeolocationForExpressWay.upLine(),
                fakeGeolocationForExpressWay.posOnNode(),
                lat,
                lng,
                speed,
                0,
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
    private void sendHighSpeedProgressionData(ExpressData data, Location location)
            throws ExecutionException, InterruptedException {
        fakeGeolocationForExpressWay fakeGeolocationForExpressWay = new fakeGeolocationForExpressWay(location);
        WhatsupApi.Params params = new WhatsupApi.Params(
                fakeUuidGenerator.gen(null),
                fakeGeolocationForExpressWay.nodeName(),
                fakeGeolocationForExpressWay.upLine(),
                fakeGeolocationForExpressWay.posOnNode(),
                data.getLat(),
                data.getLng(),
                data.getSpeed(),
                0,
                location.getBearing());
        WhatsupResultEntity r = whatsUpApiProvider.getStatics(params).toBlocking().toFuture().get();
        // return 체크 안함.
    }
}
