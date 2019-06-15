package io.github.gachonswdm.gachonbus

/*
    @name Splash
    @author Woojin Wie, Minjae Seon
    @date 2019.06.15
 */

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission

class SplashActivity : AppCompatActivity() {
    private var mDelayHandler: Handler? = null
    private val splashDelay: Long = 2000 //2 seconds

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Permission Dialog
        TedPermission.with(this)
            .setPermissionListener(permissionlistener)
            .setRationaleMessage(R.string.permission_notice)
            .setDeniedMessage(R.string.permission_denied)
            .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
            .check()
    }

    public override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

    var permissionlistener: PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            gotoNext()
        }

        override fun onPermissionDenied(deniedPermissions: List<String>) {
            gotoNext()
        }

    }

    fun gotoNext() {
        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, splashDelay)
    }
}