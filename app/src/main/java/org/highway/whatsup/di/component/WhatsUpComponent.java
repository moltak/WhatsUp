package org.highway.whatsup.di.component;

import org.highway.whatsup.actioncreator.WhatsUpActionCreator;
import org.highway.whatsup.data.scope.PerApp;
import org.highway.whatsup.domain.actioncreator.DefaultActionCreator;
import org.highway.whatsup.domain.di.component.DefaultComponent;

import dagger.Component;

/**
 * Created by engeng on 8/23/15.
 */
@PerApp
@Component(
        dependencies = {
                DefaultComponent.class,
                ServiceComponent.class
        }
)
public interface WhatsUpComponent {
        DefaultActionCreator defaultActionCreator();
        WhatsUpActionCreator whatsUpActionCreator();
}
