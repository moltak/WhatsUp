package org.highway.whatsup;

import android.support.multidex.MultiDexApplication;

import org.highway.whatsup.di.ApplicationComponent;
import org.highway.whatsup.di.ApplicationModule;
import org.highway.whatsup.di.DaggerApplicationComponent;
import org.highway.whatsup.location.DaggerLocationComponent;
import org.highway.whatsup.location.LocationComponent;
import org.highway.whatsup.location.LocationModule;

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
        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        locationComponent = DaggerLocationComponent.builder()
                .applicationComponent(applicationComponent)
                .locationModule(new LocationModule())
                .build();
    }

    public LocationComponent getLocationComponent() {
        return locationComponent;
    }
}
