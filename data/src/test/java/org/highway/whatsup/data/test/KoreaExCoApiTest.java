package org.highway.whatsup.data.test;

import org.highway.whatsup.data.constans.Keys;
import org.highway.whatsup.data.rest.KoreaExCoAccident;
import org.highway.whatsup.data.rest.KoreaExCoAdapter;
import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;

/**
 * Created by engeng on 8/22/15.
 */
public class KoreaExCoApiTest {

    TestSubscriber subscriber;

    @Before
    public void setUp() throws Exception {
        subscriber = new TestSubscriber();
    }

    @Test
    public void testAccidentApi() {
        KoreaExCoAccident.Params params = new KoreaExCoAccident.Params(
                Keys.HIGHWAYKEY_API_KEY, "2", "ex", 127.100000, 34.100000, 128.890000, 39.100000);

        KoreaExCoAdapter.getAdapter()
                .create(KoreaExCoAccident.Request.class)
                .get(params.toMap())
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();
    }
}
