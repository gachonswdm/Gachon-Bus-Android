package io.github.gachonswdm.gachonbus

/*
    @name Main
    @author Minjae Seon
    @date 2019.06.15
 */

import android.location.Location
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import io.github.gachonswdm.gachonbus.fragment.MainFragment
import io.github.gachonswdm.gachonbus.fragment.SettingFragment
import io.github.gachonswdm.gachonbus.fragment.TimelineFragment
import io.github.gachonswdm.gachonbus.util.LocationUtil.Companion.startUserLocationUpdate
import org.jetbrains.anko.toast

open class MainActivity : AppCompatActivity() {
    private var backPressedTime: Long = 0
    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // set Default Fragment
        if(savedInstanceState == null) replaceFragment(MainFragment())

        // get Fused Location Provider Client
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        // start Location Update
        startLocationUpdate()
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

    private fun startLocationUpdate() {
        // set Location Call Back Event
        val callback: LocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                if (locationResult != null) {
                    Log.d("LocationUpdate", "${locationResult.lastLocation?.latitude}, ${locationResult.lastLocation?.longitude}")
                }
            }
        }

        // start User Location Update
        startUserLocationUpdate(this, mFusedLocationProviderClient, callback)
    }
}
