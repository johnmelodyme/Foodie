package com.magnus.foodieme.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import com.magnus.foodieme.Constant.ConstantValue
import com.magnus.foodieme.R
import java.util.*

class SplashActivity : AppCompatActivity()
{
    private val TAG = ConstantValue.AppName
    var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handlingSplash(savedInstanceState)

    }

    @Suppress("DEPRECATION")
    fun handlingSplash(bundle: Bundle?)
    {
        Log.i(TAG, "handlingSplash: ")
        onFullScreen(bundle)
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, ConstantValue.MathematicalValue[0].toLong())
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