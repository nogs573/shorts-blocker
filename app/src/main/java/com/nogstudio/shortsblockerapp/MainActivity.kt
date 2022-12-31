package com.nogstudio.shortsblockerapp

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

private const val OVERLAY_PERMISSION_REQUEST_CODE = 1

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
            // The app has the permission, so you can proceed with trying overlay
            permissionGranted = 1
        }
        return permissionGranted
    }
}