package org.highway.whatsup;

import org.highway.whatsup.data.physics.SpeedMeter;
import org.highway.whatsup.data.rest.KoreaExpressCorporationApi;
import org.highway.whatsup.domain.di.component.DaggerDefaultComponent;
import org.highway.whatsup.domain.di.component.DefaultComponent;
import org.highway.whatsup.domain.di.module.KoreaExpressCorporationApiModule;
import org.highway.whatsup.domain.di.module.SpeedMeterModule;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by engeng on 8/22/15.
 */
public class KoreaExpressCorporationApiTest {

    private KoreaExpressCorporationApi api;
    private SpeedMeter speedMeter;

    @Before
    public void setUp() throws Exception {
        DefaultComponent component = DaggerDefaultComponent.builder()
                .koreaExpressCorporationApiModule(new KoreaExpressCorporationApiModule())
                .speedMeterModule(new SpeedMeterModule())
                .build();
        api = component.koreaExpressCorporationApi();
        speedMeter = component.speedMeter();
    }

    @Test
    public void testShouldHasApiObject() {
        assertThat(api, notNullValue());
        assertThat(speedMeter, notNullValue());
    }
}
