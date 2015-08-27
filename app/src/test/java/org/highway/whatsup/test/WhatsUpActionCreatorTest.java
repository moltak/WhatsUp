package org.highway.whatsup.test;

import android.content.Context;
import android.location.Location;

import org.highway.whatsup.actioncreator.WhatsUpActionCreator;
import org.highway.whatsup.di.component.ServiceComponent;
import org.highway.whatsup.di.component.DaggerServiceComponent;
import org.highway.whatsup.di.component.DaggerWhatsUpComponent;
import org.highway.whatsup.di.component.WhatsUpComponent;
import org.highway.whatsup.di.module.ServiceModule;
import org.highway.whatsup.domain.actioncreator.DefaultActionCreator;
import org.highway.whatsup.domain.data.ExpressData;
import org.highway.whatsup.domain.di.component.DaggerDefaultComponent;
import org.highway.whatsup.domain.di.component.DefaultComponent;
import org.highway.whatsup.domain.di.module.BoundsCalculatorModule;
import org.highway.whatsup.domain.di.module.DefaultActionCreatorModule;
import org.highway.whatsup.domain.di.module.SpeedMeterModule;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        Context mockAppilicationContext = mock(Context.class);
        when(mockAppilicationContext.getApplicationInfo()).thenReturn(null);
        ServiceComponent serviceComponent = DaggerServiceComponent.builder()
                .serviceModule(new ServiceModule(mockAppilicationContext))
                .build();
        component = DaggerWhatsUpComponent.builder()
                .defaultComponent(defaultComponent)
                .serviceComponent(serviceComponent)
                .build();
    }

    @Test
    public void shouldHasDefaultActionCreator() {
        DefaultActionCreator actionCreator = component.defaultActionCreator();
        assertThat(actionCreator, notNullValue());
    }

    @Test
    public void shouldReturnMsgNullBehaviorNOTHING() {
        try {
            Location mockLocation = mock(Location.class);
            when(mockLocation.getBearing()).thenReturn(30f);
            WhatsUpActionCreator creator = component.whatsUpActionCreator();
            ExpressData data = creator.doit(mockLocation, 3, lat, lng);
            assertThat(creator.getBehavior(), is(WhatsUpActionCreator.Behavior.NOTHING));
            assertThat(data.getMsg(), nullValue());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnBehaviorPrint() {
        try {
            Location mockLocation = mock(Location.class);
            when(mockLocation.getBearing()).thenReturn(30f);
            WhatsUpActionCreator creator = component.whatsUpActionCreator();
            ExpressData data = creator.doit(mockLocation, 1, lat, lng);
            assertThat(creator.getBehavior(), is(WhatsUpActionCreator.Behavior.PRINT));
            assertThat(data.getMsg(), notNullValue());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
