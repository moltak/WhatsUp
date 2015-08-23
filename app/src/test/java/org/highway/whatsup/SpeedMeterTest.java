package org.highway.whatsup;

import android.location.Location;

import org.highway.whatsup.data.physics.DefaultSpeedMeter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by engeng on 8/22/15.
 */
public class SpeedMeterTest {

    @InjectMocks Location location;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnHighSpeedPregression() {
        DefaultSpeedMeter speedMeter = new DefaultSpeedMeter();
        // 2.7m/s -> 9.72km/h (2.7 * 3.6)
        assertThat(speedMeter.getSpeedProgression(2.7f), is(DefaultSpeedMeter.Progression.LOW_SPEED));
        // 2.8m/s -> 10km/h (2.8 * 3.6)
        assertThat(speedMeter.getSpeedProgression(2.8f), is(DefaultSpeedMeter.Progression.HIGH_SPEED));
        // 5.0ms -> 18km (5 * 3.6)
        assertThat(speedMeter.getSpeedProgression(5f), is(DefaultSpeedMeter.Progression.HIGH_SPEED));
    }
}
