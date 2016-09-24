package com.example.vincent.lab1.Listener;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Vincent on 2016-09-23.
 */
public class MyLocationListener implements LocationListener {
    public String currentLocation;

    @Override
    public void onLocationChanged(Location location) {
        location.getLatitude();
        location.getLongitude();



        currentLocation = "Latitude = " + location.getLatitude() + " Longitude = " + location.getLongitude();
        Log.i("SUP BRA","WAZZZAAPPP "  + currentLocation);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
