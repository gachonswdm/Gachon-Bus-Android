package io.github.gachonswdm.gachonbus

/*
    @name Main
    @author Minjae Seon
    @date 2019.06.15
 */

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.github.gachonswdm.gachonbus.fragment.MainFragment
import io.github.gachonswdm.gachonbus.fragment.SettingFragment
import io.github.gachonswdm.gachonbus.fragment.TimelineFragment
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // set Default Fragment
        if(savedInstanceState == null) replaceFragment(MainFragment())
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
}
