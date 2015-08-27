package org.highway.whatsup.di.component;

import org.highway.whatsup.data.scope.PerApp;
import org.highway.whatsup.di.module.LocationModule;
import org.highway.whatsup.location.LocationController;

import dagger.Component;

/**
 * Created by engeng on 7/29/15.
 */
@PerApp
@Component(
        dependencies = ServiceComponent.class,
        modules = LocationModule.class)
public interface LocationComponent {
    LocationController locationController();
}
