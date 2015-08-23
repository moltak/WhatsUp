package org.highway.whatsup.domain.di.component;

import org.highway.whatsup.data.physics.BoundsCalculator;
import org.highway.whatsup.data.physics.SpeedMeter;
import org.highway.whatsup.domain.di.module.BoundsCalculatorModule;
import org.highway.whatsup.domain.function.KoExApiActionCreator;
import org.highway.whatsup.domain.di.module.KoExApiModule;
import org.highway.whatsup.domain.di.module.SpeedMeterModule;

import dagger.Component;

/**
 * Created by engeng on 8/22/15.
 */
@Component(
    modules = {
            KoExApiModule.class,
            SpeedMeterModule.class,
            BoundsCalculatorModule.class
    }
)
public interface DefaultComponent {
    KoExApiActionCreator koExApiActionCreator();
    SpeedMeter speedMeter();
    BoundsCalculator boundsCalculator();
}
