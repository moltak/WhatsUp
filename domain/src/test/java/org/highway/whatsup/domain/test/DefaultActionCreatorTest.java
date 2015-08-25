package org.highway.whatsup.domain.test;

import org.highway.whatsup.data.physics.BoundsCalculator;
import org.highway.whatsup.data.physics.SpeedMeter;
import org.highway.whatsup.data.rest.koex.KoExApiProvider;
import org.highway.whatsup.domain.data.ExpressData;
import org.highway.whatsup.domain.di.component.DaggerDefaultComponent;
import org.highway.whatsup.domain.di.component.DefaultComponent;
import org.highway.whatsup.domain.di.module.BoundsCalculatorModule;
import org.highway.whatsup.domain.di.module.DefaultActionCreatorModule;
import org.highway.whatsup.domain.di.module.SpeedMeterModule;
import org.highway.whatsup.domain.function.DefaultActionCreator;
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
public class DefaultActionCreatorTest {

    private DefaultActionCreator actionCreator;
    private SpeedMeter speedMeter;
    private BoundsCalculator boundsCalculator;
    private KoExApiProvider koExApiProvider;
    private double lat = 36.0788899991, lng = 127.10194;

    @Before
    public void setUp() throws Exception {
        DefaultComponent component = DaggerDefaultComponent.builder()
                .defaultActionCreatorModule(new DefaultActionCreatorModule())
                .speedMeterModule(new SpeedMeterModule())
                .boundsCalculatorModule(new BoundsCalculatorModule())
                .build();
        actionCreator = component.actionCreator();
        speedMeter = component.speedMeter();
        boundsCalculator = component.boundsCalculator();
        koExApiProvider = component.koExApiProvider();
    }

    @Test
    public void testShouldHasApiObject() {
        assertThat(actionCreator, notNullValue());
        assertThat(speedMeter, notNullValue());
        assertThat(boundsCalculator, notNullValue());
        assertThat(koExApiProvider, notNullValue());
    }

    @Test
    public void whenSpeedAbove10KmItHasNoExpressData() {
        SpeedMeter.Progression progressionSpeed = actionCreator.getProgression(30);
        assertThat(progressionSpeed, is(SpeedMeter.Progression.HIGH_SPEED));

        ExpressData expressData = actionCreator.getExpressWayData(30, lat, lng, progressionSpeed);
        assertThat(expressData, notNullValue());
        assertThat(expressData.getSpeed(), is(30f));
    }

    @Test
    public void whenSpeedBelow10KmItHasExpressData() {
        SpeedMeter.Progression progressionSpeed = actionCreator.getProgression(2);
        assertThat(progressionSpeed, is(SpeedMeter.Progression.LOW_SPEED));

        ExpressData expressData = actionCreator.getExpressWayData(2, lat, lng, progressionSpeed);
        assertThat(expressData, notNullValue());
        assertThat(expressData.getCctvUrl(), notNullValue());
        assertThat(expressData.getMsg(), notNullValue());
    }
}
