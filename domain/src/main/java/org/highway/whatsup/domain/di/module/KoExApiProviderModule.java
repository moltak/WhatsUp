package org.highway.whatsup.domain.di.module;

import org.highway.whatsup.data.rest.KoExApiProvider;
import org.highway.whatsup.data.rest.KoExApiProviderInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by engeng on 8/23/15.
 */
@Module
public class KoExApiProviderModule {
    @Provides KoExApiProviderInterface provideKoExApiProvider(KoExApiProvider koExApiProvider) {
        return koExApiProvider;
    }
}
