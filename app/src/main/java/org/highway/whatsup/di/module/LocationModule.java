package org.highway.whatsup.di.module;

import android.content.Context;

import org.highway.whatsup.data.scope.PerApp;
import org.highway.whatsup.location.LocationController;

import dagger.Module;
import dagger.Provides;

/**
 * Created by engeng on 7/29/15.
 */
@Module
public class LocationModule {
    @Provides LocationController provideLocationController(Context applicationContext) {
        return new LocationController(applicationContext);
    }
}
