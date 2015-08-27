package org.highway.whatsup;

import android.support.multidex.MultiDexApplication;

import org.highway.whatsup.di.component.ServiceComponent;
import org.highway.whatsup.di.component.DaggerServiceComponent;
import org.highway.whatsup.di.component.DaggerLocationComponent;
import org.highway.whatsup.di.module.ServiceModule;
import org.highway.whatsup.di.component.LocationComponent;
import org.highway.whatsup.di.module.LocationModule;

/**
 * Created by engeng on 7/31/15.
 */
public class WhatsupApplication extends MultiDexApplication {
    private LocationComponent locationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeComponents();
    }

    private void initializeComponents() {
        ServiceComponent serviceComponent = DaggerServiceComponent.builder()
                .serviceModule(new ServiceModule(this))
                .build();

        locationComponent = DaggerLocationComponent.builder()
                .serviceComponent(serviceComponent)
                .locationModule(new LocationModule())
                .build();
    }

    public LocationComponent getLocationComponent() {
        return locationComponent;
    }
}
