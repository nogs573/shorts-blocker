package com.nogstudio.shortsblockerapp

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat

private const val PERMISSION_REQUEST_CODE = 1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getOverlayPerm()

        val switch: SwitchCompat = findViewById(R.id.switch1)


    }

    private fun getOverlayPerm()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SYSTEM_ALERT_WINDOW)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Request the permission
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.SYSTEM_ALERT_WINDOW),
                PERMISSION_REQUEST_CODE)
        }
    }






}