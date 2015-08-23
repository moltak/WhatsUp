package org.highway.whatsup.domain.di.module;

import org.highway.whatsup.data.physics.DefaultBoundsCalculator;
import org.highway.whatsup.data.physics.DefaultSpeedMeter;
import org.highway.whatsup.domain.function.KoExApiActionCreator;

import dagger.Module;
import dagger.Provides;

/**
 * Created by engeng on 8/22/15.
 */
@Module
public class KoExApiModule {
    @Provides KoExApiActionCreator provideKoExApiActionCreator(
            DefaultBoundsCalculator boundsCalculator, DefaultSpeedMeter speedMeter) {

        return new KoExApiActionCreator(boundsCalculator, speedMeter);
    }
}
