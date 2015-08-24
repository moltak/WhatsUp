package org.highway.whatsup.data.test;

import org.highway.whatsup.data.constans.Keys;
import org.highway.whatsup.data.rest.koex.functions.KoExCctvApi;
import org.highway.whatsup.data.rest.koex.functions.KoExAccidentApi;
import org.highway.whatsup.data.rest.koex.adapter.KoExRestAdapter;
import org.highway.whatsup.data.rest.koex.functions.KoExEventApi;
import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by engeng on 8/22/15.
 */
public class KoExApiTest {

    TestSubscriber subscriber;

    @Before
    public void setUp() throws Exception {
        subscriber = new TestSubscriber();
    }

    @Test
    public void testAccidentApi() {
        KoExAccidentApi.Params params = new KoExAccidentApi.Params(
                Keys.HIGHWAYKEY_API_KEY, "2", "ex", 127.100000, 34.100000, 128.890000, 39.100000);

        KoExRestAdapter.getAdapter()
                .create(KoExAccidentApi.Request.class)
                .get(params.toMap())
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();

        KoExAccidentApi.Response r = (KoExAccidentApi.Response) subscriber.getOnNextEvents().get(0);
        assertThat(r.getRs(), nullValue());
    }

    @Test
    public void testEventApi() {
        KoExEventApi.Params params = new KoExEventApi.Params(
                Keys.HIGHWAYKEY_API_KEY, "2", "ex", 127.100000, 34.100000, 128.890000, 39.100000
        );

        KoExRestAdapter.getAdapter()
                .create(KoExEventApi.Request.class)
                .get(params.toMap())
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();
    }

    @Test
    public void testCctvApi() {
        KoExCctvApi.Params params = new KoExCctvApi.Params(
                Keys.HIGHWAYKEY_API_KEY, "2", "ex", 127.100000, 34.100000, 128.890000, 39.100000
        );

        KoExRestAdapter.getAdapter()
                .create(KoExCctvApi.Request.class)
                .get(params.toMap())
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();
    }
}
