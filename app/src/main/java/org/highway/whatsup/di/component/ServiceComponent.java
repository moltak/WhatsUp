package org.highway.whatsup.di.component;

import android.content.Context;

import org.highway.whatsup.di.module.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by engeng on 7/29/15.
 */
@Singleton
@Component(modules = ServiceModule.class)
public interface ServiceComponent {

    // Exposed to sub-graphs.
    Context applicationContext();
}
