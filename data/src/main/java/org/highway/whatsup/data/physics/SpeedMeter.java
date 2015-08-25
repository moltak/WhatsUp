package org.highway.whatsup.data.physics;

/**
 * Created by engeng on 8/22/15.
 */
public interface SpeedMeter {
    Progression getSpeedProgression(float speed);

    enum Progression {
        HIGH_SPEED, LOW_SPEED
    }
}
