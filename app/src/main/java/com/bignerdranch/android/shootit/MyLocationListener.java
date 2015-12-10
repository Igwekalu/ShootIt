package com.bignerdranch.android.shootit;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by igweigwe-kalu on 12/9/15.
 */
public class MyLocationListener implements LocationListener {

    private double MyLatitude;
    private double MyLongitude;
    private double currentLongitude;
    private double currentLatitude;

    @Override
    public void onLocationChanged(Location location) {
        MyLatitude = location.getLatitude();
        MyLongitude = location.getLongitude();

        if (MyLatitude != currentLatitude){
            currentLatitude = MyLatitude;
        }

        if(MyLongitude != currentLongitude){
            currentLongitude = MyLongitude;
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
