package org.highway.whatsup.location;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.highway.whatsup.bus.BusProvider;

import javax.inject.Inject;

public class LocationController
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener, LocationRetriever {

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private int interval = 10000;
    private int locationPriority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY;
    private Location lastLocation;
    private boolean isService = false;

    @Inject public LocationController(Context context) {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        lastLocationInit();
    }

    private void lastLocationInit() {
        lastLocation = new Location("");
        lastLocation.setLatitude(0.0);
        lastLocation.setLongitude(0.0);
    }

    @Override
    public void locationClientConnect() {
        if(!googleApiClient.isConnected()) {
            googleApiClient.connect();
        }
    }

    @Override
    public void locationClientDisconnect() {
        if(googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if(isLocationStarted()) {
            locationUpdateStart(isService);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {}

    private boolean isLocationStarted() {
        return locationRequest != null;
    }

    private void locationSetting() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(interval);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(locationPriority);
    }

    @Override
    public void locationUpdateStart(boolean isService) {
        this.isService = isService;
        locationSetting();
        if(googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    googleApiClient, locationRequest, this);
        } else {
            locationClientConnect();
        }
    }

    @Override
    public void locationUpdateStop(boolean isService) {
        if(this.isService == isService) {
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    googleApiClient, this);
        }
    }

    @Override
    public void setLocationParams(int interval, int locationPriority) {
        this.interval = interval;
        this.locationPriority = locationPriority;
    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        BusProvider.getUIBusInstance().post(new LocationEvent(location));
    }

    public Location getLastLocation() {
        return lastLocation;
    }
}
