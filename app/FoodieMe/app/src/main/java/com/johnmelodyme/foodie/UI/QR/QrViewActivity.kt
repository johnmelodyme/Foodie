package com.johnmelodyme.foodie.UI.QR

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import com.google.android.gms.vision.CameraSource
import com.johnmelodyme.foodie.R
import com.johnmelodyme.foodie.UI.MainActivity
import kotlin.system.exitProcess

class QrViewActivity : AppCompatActivity()
{
    private lateinit var qrResult: TextView
    private lateinit var result: String
    private lateinit var parsedValue: Bundle
    private lateinit var cameraSource: CameraSource

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_view)

        // * Render User Interface
        initUi(savedInstanceState)
    }

    override fun onBackPressed()
    {
        finishActivity(2)
        val home = Intent(this, MainActivity::class.java)
        startActivity(home)
        finish()
    }


    override fun finish()
    {
        super.finishActivity(2)
        val home = Intent(this, MainActivity::class.java)
        startActivity(home)
    }


    private fun initUi(bundle: Bundle?)
    {
        parsedValue = intent.extras!!
        qrResult = findViewById(R.id.qr_result)
        result = parsedValue.getString("value").toString()
        qrResult.text = result
        println(result)
    }

    fun onClickQr(view: View)
    {
        cameraSource.stop()
        cameraSource.release()
        val home = Intent(this, MainActivity::class.java)
        startActivity(home)
        this.finish()
    }
}