package org.highway.whatsup.location;

import android.content.Context;

import org.highway.whatsup.di.scope.ForApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by engeng on 7/29/15.
 */
@Module
public class LocationModule {
    @Provides @ForApplication
    LocationController provideLocationController(Context applicationContext) {
        return new LocationController(applicationContext);
    }
}
