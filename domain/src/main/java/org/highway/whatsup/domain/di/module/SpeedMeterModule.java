package org.highway.whatsup.domain.di.module;

import org.highway.whatsup.data.physics.DefaultSpeedMeter;
import org.highway.whatsup.data.physics.SpeedMeter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by engeng on 8/22/15.
 */
@Module
public class SpeedMeterModule {
    @Provides SpeedMeter provideDefaultSpeedMeter(DefaultSpeedMeter defaultSpeedMeter) {
        return defaultSpeedMeter;
    }
}
