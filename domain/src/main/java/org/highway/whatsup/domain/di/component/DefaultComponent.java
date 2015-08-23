package org.highway.whatsup.domain.di.component;

import org.highway.whatsup.data.physics.SpeedMeter;
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
            SpeedMeterModule.class
    }
)
public interface DefaultComponent {
    KoExApiActionCreator koreaExpressCorporationApi();
    SpeedMeter speedMeter();
}
