package io.github.gachonswdm.gachonbus

/*
    @name Main
    @author Minjae Seon
    @date 2019.06.15
 */

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import io.github.gachonswdm.gachonbus.fragment.MainFragment
import io.github.gachonswdm.gachonbus.fragment.SettingFragment
import io.github.gachonswdm.gachonbus.fragment.TimelineFragment
import org.jetbrains.anko.toast
import java.lang.Exception

open class MainActivity : AppCompatActivity() {
    private var backPressedTime: Long = 0
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var mLocationRequest: LocationRequest
    private lateinit var mLocationCallback: LocationCallback
    private var locationUpdateInterval = (2 * 1000).toLong()
    private var locationFastestInterval: Long = 2000
    data class UserLocation(var Latitude: Double, var Longitude: Double)
    private var mUserLocation: UserLocation = UserLocation(0.0, 0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // set Default Fragment
        if(savedInstanceState == null) replaceFragment(MainFragment())

        // User Location Update Check
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        startUserLocationUpdate()
    }

    // Set Bottom Navigation Bar
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_main -> {
                replaceFragment(MainFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_timeline -> {
                replaceFragment(TimelineFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_setting -> {
                replaceFragment(SettingFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onBackPressed() {
        val tempTime: Long = System.currentTimeMillis()
        val intervalTime: Long = tempTime - backPressedTime

        if (intervalTime in 0..2000) {
            super.onBackPressed()
        }
        else {
            backPressedTime = tempTime
            toast(R.string.backToast)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }

    protected fun startUserLocationUpdate() {
        mLocationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(locationUpdateInterval)
            .setFastestInterval(locationFastestInterval)
        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                onLocationChange(locationResult?.lastLocation)
            }
        }

        val finePermission: Int = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        if (finePermission == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.requestLocationUpdates(
                mLocationRequest,
                mLocationCallback,
                null)
        }
    }

    fun getUserLastLocation() {
        val finePermission: Int = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val coarsePermission: Int = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

        // Check Location Permission
        if (finePermission == PackageManager.PERMISSION_GRANTED && coarsePermission == PackageManager.PERMISSION_GRANTED) {
            // Get Location's Latitude & Longitude
            mFusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
                if (location != null) {
                    onLocationChange(location)
                }
            }
            mFusedLocationClient.lastLocation.addOnFailureListener { e: Exception ->
                e.stackTrace
            }
        }
    }

    fun onLocationChange(location: Location?) {
        mUserLocation = UserLocation(location!!.latitude, location!!.longitude)
        Log.d("onLocationChange", "$mUserLocation")
    }
}
