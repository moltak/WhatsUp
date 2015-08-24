package org.highway.whatsup.domain.di.component;

import org.highway.whatsup.domain.function.DefaultActionCreator;
import org.highway.whatsup.domain.function.WhatsUpActionCreator;

import dagger.Component;

/**
 * Created by engeng on 8/23/15.
 */
@Component(
        dependencies = DefaultComponent.class
)
public interface WhatsUpComponent {
        DefaultActionCreator defaultActionCreator();
        WhatsUpActionCreator whatsUpActionCreator();
}
