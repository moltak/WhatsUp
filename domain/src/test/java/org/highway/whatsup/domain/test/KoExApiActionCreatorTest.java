package org.highway.whatsup.domain.test;

import org.highway.whatsup.data.physics.BoundsCalculator;
import org.highway.whatsup.data.physics.SpeedMeter;
import org.highway.whatsup.domain.data.ExpressData;
import org.highway.whatsup.domain.di.component.DaggerDefaultComponent;
import org.highway.whatsup.domain.di.component.DefaultComponent;
import org.highway.whatsup.domain.di.module.BoundsCalculatorModule;
import org.highway.whatsup.domain.di.module.KoExApiModule;
import org.highway.whatsup.domain.di.module.SpeedMeterModule;
import org.highway.whatsup.domain.function.KoExApiActionCreator;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by engeng on 8/22/15.
 */
public class KoExApiActionCreatorTest {

    private KoExApiActionCreator actionCreator;
    private SpeedMeter speedMeter;
    private BoundsCalculator boundsCalculator;
    private double lat = 36.0788899991, lng = 127.10194;

    @Before
    public void setUp() throws Exception {
        DefaultComponent component = DaggerDefaultComponent.builder()
                .koExApiModule(new KoExApiModule())
                .speedMeterModule(new SpeedMeterModule())
                .boundsCalculatorModule(new BoundsCalculatorModule())
                .build();
        actionCreator = component.koExApiActionCreator();
        speedMeter = component.speedMeter();
        boundsCalculator = component.boundsCalculator();
    }

    @Test
    public void testShouldHasApiObject() {
        assertThat(actionCreator, notNullValue());
        assertThat(speedMeter, notNullValue());
        assertThat(boundsCalculator, notNullValue());
    }

    @Test
    public void whenSpeedAbove10KmItHasNoExpressData() {
        SpeedMeter.Progression progression = actionCreator.getProgression(30);
        assertThat(progression, is(SpeedMeter.Progression.HIGH_SPEED));

        ExpressData expressData = actionCreator.getExpressWayData(lat, lng);
        assertThat(expressData, nullValue());
    }

    @Test
    public void whenSpeedBelow10KmItHasExpressData() {
        SpeedMeter.Progression progression = actionCreator.getProgression(2);
        assertThat(progression, is(SpeedMeter.Progression.LOW_SPEED));

        ExpressData expressData = actionCreator.getExpressWayData(lat, lng);
        assertThat(expressData, notNullValue());

        assertThat(expressData.getCctvUrl(), not(""));
        assertThat(expressData.getMsg(), not(""));
    }
}
