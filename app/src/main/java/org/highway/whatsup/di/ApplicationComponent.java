package org.highway.whatsup.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by engeng on 7/29/15.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    // Exposed to sub-graphs.
    Context applicationContext();
}
