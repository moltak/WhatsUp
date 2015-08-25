package org.highway.whatsup.data.test;

import org.highway.whatsup.data.entity.whatsup.WhatsupResultEntity;
import org.highway.whatsup.data.rest.whatsup.adapter.WhatsUpRestAdapter;
import org.highway.whatsup.data.rest.whatsup.functions.WhatsupApi;
import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by engeng on 8/24/15.
 */
public class WhatsupApiTest {

    String uuid = "uuid", nodeName = "중앙고속도로";
    boolean upLine = true;
    double posOnNode = 170;
    double lat = 34.100000, lon = 127.100000, speed = 27, acceleration = 10, orientation = 90;

    private WhatsupApi.Params params;
    private TestSubscriber<WhatsupResultEntity> subscriber;

    @Before
    public void setUp() throws Exception {
        params = new WhatsupApi.Params(
                uuid, nodeName, upLine, posOnNode, lat, lon, speed, acceleration, orientation);
        subscriber = new TestSubscriber<>();
    }

    @Test
    public void shoulStaticsdReturnCode200() {
        WhatsUpRestAdapter.getAdapter()
                .create(WhatsupApi.Request.class)
                .statics(params.toMap())
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
        WhatsupResultEntity r = subscriber.getOnNextEvents().get(0);
        assertThat(r.getCode(), is(200));
    }

    @Test
    public void shoulWhatsupdReturnCode200() {
        WhatsUpRestAdapter.getAdapter()
                .create(WhatsupApi.Request.class)
                .whatsup(params.toMap())
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
        WhatsupResultEntity r = subscriber.getOnNextEvents().get(0);
        assertThat(r.getCode(), is(200));
    }
}
