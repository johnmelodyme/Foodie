package com.johnmelodyme.foodie.UI

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.johnmelodyme.foodie.Functions.Methods
import com.johnmelodyme.foodie.R

class SplashActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // * Set Splash Full Screen
        onFullScreen(savedInstanceState)

        // * Run SplashHandler
        Methods.handlingSplash(savedInstanceState, this)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.fade_out)
    }

    @Suppress("DEPRECATION")
    fun onFullScreen(bundle: Bundle?)
    {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}