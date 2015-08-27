package org.highway.whatsup.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.location.LocationRequest;
import com.squareup.otto.Subscribe;

import org.highway.whatsup.WhatsupApplication;
import org.highway.whatsup.bus.BusProvider;
import org.highway.whatsup.location.LocationController;
import org.highway.whatsup.location.LocationEvent;

import javax.inject.Inject;

public class LocationUpdateService extends Service {

    @Inject LocationController locationController;
    private final int updateTime = 5000;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BusProvider.getUIBusInstance().register(this);
        ((WhatsupApplication)getApplication()).getLocationComponent().inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        locationController.setLocationParams(updateTime, LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationController.locationUpdateStart(true);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        BusProvider.getUIBusInstance().unregister(this);
        locationController.locationUpdateStop(true);
        super.onDestroy();
    }

    @Subscribe
    public void onUpdateLocationReceive(LocationEvent event) {
        Log.d("onUpdateLocationReceive", "location = " + event.getLocation());
    }
}
