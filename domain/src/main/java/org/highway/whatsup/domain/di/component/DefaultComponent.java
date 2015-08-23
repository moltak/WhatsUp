package org.highway.whatsup.domain.di.component;

import org.highway.whatsup.data.physics.SpeedMeter;
import org.highway.whatsup.data.rest.KoreaExpressCorporationApi;
import org.highway.whatsup.domain.di.module.KoreaExpressCorporationApiModule;
import org.highway.whatsup.domain.di.module.SpeedMeterModule;

import dagger.Component;

/**
 * Created by engeng on 8/22/15.
 */
@Component(
    modules = {
            KoreaExpressCorporationApiModule.class,
            SpeedMeterModule.class
    }
)
public interface DefaultComponent {
    KoreaExpressCorporationApi koreaExpressCorporationApi();
    SpeedMeter speedMeter();
}
