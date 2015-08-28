package org.highway.whatsup.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.location.LocationRequest;

import org.highway.whatsup.actioncreator.WhatsUpActionCreator;
import org.highway.whatsup.di.component.DaggerLocationComponent;
import org.highway.whatsup.di.component.DaggerServiceComponent;
import org.highway.whatsup.di.component.DaggerWhatsUpComponent;
import org.highway.whatsup.di.component.LocationComponent;
import org.highway.whatsup.di.component.ServiceComponent;
import org.highway.whatsup.di.component.WhatsUpComponent;
import org.highway.whatsup.di.module.LocationModule;
import org.highway.whatsup.di.module.ServiceModule;
import org.highway.whatsup.domain.data.ExpressData;
import org.highway.whatsup.domain.di.component.DaggerDefaultComponent;
import org.highway.whatsup.domain.di.component.DefaultComponent;
import org.highway.whatsup.domain.di.module.BoundsCalculatorModule;
import org.highway.whatsup.domain.di.module.DefaultActionCreatorModule;
import org.highway.whatsup.domain.di.module.SpeedMeterModule;
import org.highway.whatsup.location.LocationController;
import org.highway.whatsup.ui.activity.AlertActivity;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainService extends Service {

    private WhatsUpActionCreator whatsUpActionCreator;
    private LocationController locationController;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceComponent serviceComponent = DaggerServiceComponent.builder()
                .serviceModule(new ServiceModule(getApplicationContext()))
                .build();

        LocationComponent locationComponent = DaggerLocationComponent.builder()
                .locationModule(new LocationModule())
                .build();

        DefaultComponent defaultComponent = DaggerDefaultComponent.builder()
                .defaultActionCreatorModule(new DefaultActionCreatorModule())
                .boundsCalculatorModule(new BoundsCalculatorModule())
                .speedMeterModule(new SpeedMeterModule())
                .build();

        WhatsUpComponent whatsUpComponent = DaggerWhatsUpComponent.builder()
                .serviceComponent(serviceComponent)
                .locationComponent(locationComponent)
                .defaultComponent(defaultComponent)
                .build();

        whatsUpActionCreator = whatsUpComponent.whatsUpActionCreator();
        locationController = whatsUpComponent.locationController();

        startIntervalCheckup(6);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        locationController.setLocationParams(30 * 1000, LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationController.locationUpdateStart(true);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        locationController.locationUpdateStop(true);
        super.onDestroy();
    }

    private void startIntervalCheckup(int delay) {
        Observable.timer(delay, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        check();
                    }
                });
    }

    private void check() {
        Location location = locationController.getLastLocation();
        try {
            ExpressData data = whatsUpActionCreator.doit(
                    location, location.getSpeed(), location.getLatitude(), location.getLongitude());
            if (whatsUpActionCreator.getBehavior() == WhatsUpActionCreator.Behavior.PRINT) {
                startAlertActivity(data);
                startIntervalCheckup(5);
                Log.d("Log", data.toString());
            } else {
                startIntervalCheckup(1);
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void startAlertActivity(ExpressData data) {
        Intent i = new Intent(this, AlertActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("data", data);
        startActivity(i);
    }
}
