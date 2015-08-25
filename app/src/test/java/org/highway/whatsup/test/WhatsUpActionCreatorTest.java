package org.highway.whatsup.test;

import org.highway.whatsup.actioncreator.WhatsUpActionCreator;
import org.highway.whatsup.di.component.DaggerWhatsUpComponent;
import org.highway.whatsup.di.component.WhatsUpComponent;
import org.highway.whatsup.domain.actioncreator.DefaultActionCreator;
import org.highway.whatsup.domain.di.component.DaggerDefaultComponent;
import org.highway.whatsup.domain.di.component.DefaultComponent;
import org.highway.whatsup.domain.di.module.BoundsCalculatorModule;
import org.highway.whatsup.domain.di.module.DefaultActionCreatorModule;
import org.highway.whatsup.domain.di.module.SpeedMeterModule;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by engeng on 8/23/15.
 */
public class WhatsUpActionCreatorTest {

    private WhatsUpComponent component;
    private double lat = 36.0788899991, lng = 127.10194;

    @Before
    public void setUp() throws Exception {
        DefaultComponent defaultComponent = DaggerDefaultComponent.builder()
                .boundsCalculatorModule(new BoundsCalculatorModule())
                .speedMeterModule(new SpeedMeterModule())
                .defaultActionCreatorModule(new DefaultActionCreatorModule())
                .build();
        component = DaggerWhatsUpComponent.builder()
                .defaultComponent(defaultComponent)
                .build();
    }

    @Test
    public void shouldHasDefaultActionCreator() {
        DefaultActionCreator actionCreator = component.defaultActionCreator();
        assertThat(actionCreator, notNullValue());
    }

    @Test
    public void shouldHasWhatsupApiModule() {
        WhatsUpActionCreator whatsUpActionCreator = component.whatsUpActionCreator();
        assertThat(whatsUpActionCreator, notNullValue());
        assertThat(whatsUpActionCreator.getDefaultActionCreator(), notNullValue());
        assertThat(whatsUpActionCreator.getWhatsUpApiProvider(), notNullValue());
    }
}
