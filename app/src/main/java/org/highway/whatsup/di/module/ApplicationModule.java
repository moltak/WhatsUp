package org.highway.whatsup.di.module;

import android.content.Context;

import org.highway.whatsup.WhatsupApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by engeng on 7/29/15.
 */
@Module
public class ApplicationModule {
    private final Context applicationContext;

    public ApplicationModule(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return applicationContext;
    }
}
