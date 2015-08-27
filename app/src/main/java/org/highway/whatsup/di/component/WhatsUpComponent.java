package org.highway.whatsup.di.component;

import org.highway.whatsup.actioncreator.WhatsUpActionCreator;
import org.highway.whatsup.data.scope.PerApp;
import org.highway.whatsup.domain.actioncreator.DefaultActionCreator;
import org.highway.whatsup.domain.di.component.DefaultComponent;
import org.highway.whatsup.location.LocationController;

import dagger.Component;

/**
 * Created by engeng on 8/23/15.
 */
@PerApp
@Component(
        dependencies = {
                ServiceComponent.class,
                DefaultComponent.class,
                LocationComponent.class
        }
)
public interface WhatsUpComponent {
        WhatsUpActionCreator whatsUpActionCreator();
        LocationController locationController();
}
