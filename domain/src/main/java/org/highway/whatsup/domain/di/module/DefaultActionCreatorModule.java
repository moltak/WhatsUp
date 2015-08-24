package org.highway.whatsup.domain.di.module;

import org.highway.whatsup.data.physics.DefaultBoundsCalculator;
import org.highway.whatsup.data.physics.DefaultSpeedMeter;
import org.highway.whatsup.data.rest.koex.KoExApiProvider;
import org.highway.whatsup.domain.function.DefaultActionCreator;

import dagger.Module;
import dagger.Provides;

/**
 * Created by engeng on 8/22/15.
 */
@Module
public class DefaultActionCreatorModule {
    @Provides
    DefaultActionCreator provideActionCreator(
            DefaultBoundsCalculator boundsCalculator, DefaultSpeedMeter speedMeter, KoExApiProvider koExApiProvider) {

        return new DefaultActionCreator(boundsCalculator, speedMeter, koExApiProvider);
    }
}
