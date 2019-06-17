package io.github.gachonswdm.gachonbus.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
class LocationUtil {
    companion object {
        private lateinit var mLocationRequest: LocationRequest
        private var locationUpdateInterval = (2 * 1000).toLong()
        private var locationFastestInterval: Long = 2000

        fun startUserLocationUpdate(context: Context, mFusedLocationClient: FusedLocationProviderClient, mLocationCallback: LocationCallback) {
            mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(locationUpdateInterval)
                .setFastestInterval(locationFastestInterval)


            val finePermission: Int = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            if (finePermission == PackageManager.PERMISSION_GRANTED) {
                mFusedLocationClient.requestLocationUpdates(
                    mLocationRequest,
                    mLocationCallback,
                    null
                )
            }
        }
    }
}