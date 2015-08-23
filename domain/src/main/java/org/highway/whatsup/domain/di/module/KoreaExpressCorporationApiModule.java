package org.highway.whatsup.domain.di.module;

import org.highway.whatsup.data.physics.DefaultBoundsCalculator;
import org.highway.whatsup.data.rest.KoreaExpressCorporationApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by engeng on 8/22/15.
 */
@Module
public class KoreaExpressCorporationApiModule {
    @Provides KoreaExpressCorporationApi providesKoreaExpressCorporationApi(
            DefaultBoundsCalculator defaultBoundsCalculator) {
        return new KoreaExpressCorporationApi(defaultBoundsCalculator);
    }
}
