package com.nogstudio.shortsblockerapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat

private const val PERMISSION_REQUEST_CODE = 1
private const val OVERLAY_PERMISSION_REQUEST_CODE = 2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startIntent = Intent(this, ServiceOverride::class.java)
        val stopIntent = Intent(this, ServiceOverride::class.java)

        val switch: SwitchCompat = findViewById(R.id.switch1)
        switch.setOnCheckedChangeListener { _, isChecked ->
            // If the switch is checked and getOverlayPerm() returns 1
            if (isChecked && getOverlayPerm() == 1) {
                // Start the ServiceOverride service
                println("STARTING SERVICE")
                startService(startIntent)
            }
            //if the switch is not checked
            else if (!isChecked)
            {
                println("STOPPING SERVICE")
                stopService(stopIntent)
            }
        }
    }

    private fun getOverlayPerm(): Int {
        var permissionGranted = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName"))
            startActivityForResult(intent, OVERLAY_PERMISSION_REQUEST_CODE)
        } else {
            // The app has the permission, so you can proceed with your desired action
            permissionGranted = 1
        }
        return permissionGranted
    }

//    private fun getOverlayPerm(): Int
//    {
//        var permissionGranted = 0
//        //check if permission was previously granted
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.SYSTEM_ALERT_WINDOW)
//            != PackageManager.PERMISSION_GRANTED)
//        {
//            // Permission was not granted
//            // Check if the user has previously denied the permission
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.SYSTEM_ALERT_WINDOW))
//            {
//                // The user has previously denied the permission, so do nothing
//                //**consider sending a message to user here
//            }
//            else
//            {
//                println("GOT HERE")
//                // The user has not previously denied the permission, so request it
//                ActivityCompat.requestPermissions(this,
//                    arrayOf(Manifest.permission.SYSTEM_ALERT_WINDOW),
//                    PERMISSION_REQUEST_CODE)
//                //check the result
//                if (ContextCompat.checkSelfPermission(this,
//                        Manifest.permission.SYSTEM_ALERT_WINDOW)
//                    == PackageManager.PERMISSION_GRANTED)
//                {
//                    permissionGranted = 1
//                }
//            }
//        }
//        return permissionGranted
//    }


}