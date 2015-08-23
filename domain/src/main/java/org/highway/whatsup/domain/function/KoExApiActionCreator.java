package org.highway.whatsup.domain.function;

import org.highway.whatsup.data.physics.BoundsCalculator;
import org.highway.whatsup.data.physics.SpeedMeter;

/**
 * Created by engeng on 8/22/15.
 */
public class KoExApiActionCreator {

    private final BoundsCalculator boundsCalculator;
    private final SpeedMeter speedMeter;

    public KoExApiActionCreator(BoundsCalculator boundsCalculator, SpeedMeter speedMeter) {
        this.boundsCalculator = boundsCalculator;
        this.speedMeter = speedMeter;
    }

    public SpeedMeter.Progression getProgression(float speed) {
        return speedMeter.getSpeedProgression(speed);
    }

    public void getExpressWayData() {

    }
}
