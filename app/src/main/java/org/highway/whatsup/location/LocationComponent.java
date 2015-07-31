package org.highway.whatsup.location;

import org.highway.whatsup.di.ApplicationComponent;
import org.highway.whatsup.di.scope.ForApplication;
import org.highway.whatsup.service.LocationUpdateService;

import dagger.Component;

/**
 * Created by engeng on 7/29/15.
 */
@ForApplication
@Component(
        dependencies = ApplicationComponent.class,
        modules = LocationModule.class)
public interface LocationComponent {
    void inject(LocationUpdateService locationUpdateService);
}
