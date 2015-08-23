package org.highway.whatsup.data.physics;

/**
 * Created by engeng on 8/22/15.
 */
public interface SpeedMeter {
    public Progression getSpeedProgression(float speed);

    public enum Progression {
        HIGH_SPEED, LOW_SPEED
    }
}
