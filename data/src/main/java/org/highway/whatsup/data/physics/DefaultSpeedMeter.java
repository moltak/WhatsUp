package org.highway.whatsup.data.physics;

/**
 * Created by engeng on 8/22/15.
 */
public class DefaultSpeedMeter implements SpeedMeter {
    /**
     * speed = m/s -> speed * 3.6 = km/s
     * speed에 3.6을 곱하면 시간당 속도가 나옴.
     * @param speed m/s
     * @return LOW_SPEED, HIGH_SPEED
     */
    @Override
    public SpeedMeter.Progression getSpeedProgression(float speed) {
        return (speed * 3.6) <= 10 ? SpeedMeter.Progression.LOW_SPEED : SpeedMeter.Progression.HIGH_SPEED;
    }
}
