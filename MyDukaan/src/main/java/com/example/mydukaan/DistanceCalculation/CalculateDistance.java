package com.example.mydukaan.DistanceCalculation;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

public class CalculateDistance {
    Context context;
    public CalculateDistance(Context context) {
        this.context = context;

    }
    public double getdistance(LatLng start, LatLng end){
        double distance = SphericalUtil.computeDistanceBetween(start, end);
        return distance/1000;
    }

}
