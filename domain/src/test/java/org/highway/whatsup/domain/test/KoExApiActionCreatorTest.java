package org.highway.whatsup.domain.test;

import org.highway.whatsup.data.physics.SpeedMeter;
import org.highway.whatsup.domain.function.KoExApiActionCreator;
import org.highway.whatsup.domain.di.component.DaggerDefaultComponent;
import org.highway.whatsup.domain.di.component.DefaultComponent;
import org.highway.whatsup.domain.di.module.KoExApiModule;
import org.highway.whatsup.domain.di.module.SpeedMeterModule;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by engeng on 8/22/15.
 */
public class KoExApiActionCreatorTest {

    private KoExApiActionCreator api;
    private SpeedMeter speedMeter;

    @Before
    public void setUp() throws Exception {
        DefaultComponent component = DaggerDefaultComponent.builder()
                .koExApiModule(new KoExApiModule())
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
