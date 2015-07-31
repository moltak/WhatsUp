package org.highway.whatsup.di;

import android.app.Activity;

import org.highway.whatsup.di.scope.ForActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by engeng on 7/29/15.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides @ForActivity
    Activity activity() {
        return activity;
    }
}
