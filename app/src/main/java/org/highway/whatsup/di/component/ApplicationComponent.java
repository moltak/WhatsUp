package org.highway.whatsup.di.component;

import android.content.Context;

import org.highway.whatsup.di.module.ApplicationModule;

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
