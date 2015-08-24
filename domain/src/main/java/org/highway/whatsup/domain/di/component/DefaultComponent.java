package org.highway.whatsup.domain.di.component;

import org.highway.whatsup.data.physics.BoundsCalculator;
import org.highway.whatsup.data.physics.SpeedMeter;
import org.highway.whatsup.data.rest.koex.KoExApiProvider;
import org.highway.whatsup.domain.di.module.BoundsCalculatorModule;
import org.highway.whatsup.domain.di.module.DefaultActionCreatorModule;
import org.highway.whatsup.domain.di.module.SpeedMeterModule;
import org.highway.whatsup.domain.function.DefaultActionCreator;

import dagger.Component;

/**
 * Created by engeng on 8/22/15.
 */
@Component(
    modules = {
            DefaultActionCreatorModule.class,
            SpeedMeterModule.class,
            BoundsCalculatorModule.class
    }
)
public interface DefaultComponent {
    DefaultActionCreator actionCreator();
    SpeedMeter speedMeter();
    BoundsCalculator boundsCalculator();
    KoExApiProvider koExApiProvider();
}
