package org.highway.whatsup.domain.di.module;

import org.highway.whatsup.data.physics.BoundsCalculator;
import org.highway.whatsup.data.physics.DefaultBoundsCalculator;

import dagger.Module;
import dagger.Provides;

/**
 * Created by engeng on 8/23/15.
 */
@Module
public class BoundsCalculatorModule {
    @Provides BoundsCalculator provideBoundsCalculator(DefaultBoundsCalculator boundsCalculator) {
        return boundsCalculator;
    }
}
