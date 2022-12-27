package com.nogstudio.shortsblockerapp

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button

class ServiceOverride: Service() {

    private lateinit var windowManager: WindowManager
    private lateinit var button: Button

    override fun onCreate() {
        super.onCreate()

        // Get an instance of the WindowManager class.
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val button = Button(this)
        button.setText("Overlayed button")
        button.setBackgroundColor(Color.RED)

        // Set the button's size, position, and appearance using layout parameters.
        val layoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT, //width
            WindowManager.LayoutParams.WRAP_CONTENT, //height
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, //type
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, //flag
            PixelFormat.TRANSLUCENT //format
        )
        layoutParams.gravity = Gravity.TOP or Gravity.LEFT //position
        layoutParams.x = 0
        layoutParams.y = 100

        // Add the button to the window.
        windowManager.addView(button, layoutParams)

        //----------------- If I wanted to add functionality to the button ---------------
        // Set an OnClickListener for the button.
        button.setOnClickListener {
            // Perform the desired action when the button is clicked.
            println("Button was pressed")
        }

    }
    override fun onBind(intent: Intent): IBinder? {
        // Return the communication channel to the service.
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int
    {
        // Perform any required initialization or setup here.
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        // Perform any required cleanup or teardown here.
        super.onDestroy()
        // Remove the button from the window when the service is destroyed.
        windowManager.removeView(button)
    }


}