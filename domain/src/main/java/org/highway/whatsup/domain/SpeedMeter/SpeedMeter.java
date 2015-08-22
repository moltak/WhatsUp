package org.highway.whatsup.domain.SpeedMeter;

/**
 * Created by engeng on 8/22/15.
 */
public class SpeedMeter {
    public Progression getSpeedProgression(float speed) {
        return (speed * 3.6) <= 10 ? Progression.LOW_SPEED : Progression.HIGH_SPEED;
    }

    public enum Progression {
        HIGH_SPEED, LOW_SPEED
    }
}
