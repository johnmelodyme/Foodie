package com.johnmelodyme.foodie.UI.QR

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.util.isNotEmpty
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.johnmelodyme.foodie.Constant.ConstantValue
import com.johnmelodyme.foodie.R
import com.johnmelodyme.foodie.UI.MainActivity
import com.johnmelodyme.foodie.UI.ProfileActivity
import com.johnmelodyme.foodie.UI.SearchActivity

class QRActivity : AppCompatActivity()
{
    private val SCANNER_CODE = ConstantValue.QR_SCANNER[2]
    private val TAG = ConstantValue.AppName
    private lateinit var cameraSource: CameraSource
    private lateinit var detector: BarcodeDetector
    private lateinit var value: String

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q_r)

        // * Initialise User Interface
        initUserInterface()

        if (ContextCompat.checkSelfPermission(
                    this@QRActivity,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
        )
        {
            requestCameraPermission()
        }
        else
        {
            controlSetup()
        }

        controlSetup()
    }

    private fun requestCameraPermission()
    {
        ActivityCompat.requestPermissions(
            this@QRActivity,
            arrayOf(Manifest.permission.CAMERA),
            SCANNER_CODE
        )
    }

    private fun controlSetup()
    {
        val surface_view: SurfaceView = findViewById(R.id.surface_view)

        detector = BarcodeDetector.Builder(this@QRActivity).build()
        cameraSource =
            CameraSource.Builder(
                this@QRActivity, detector
            )
                .setAutoFocusEnabled(true)
                .build()

        surface_view.holder.addCallback(surfaceCallBack)
        detector.setProcessor(processor)
    }

    override fun finish()
    {
        super.finish()
        overridePendingTransition(0, R.anim.fade_out)
    }

    // * User Interface Renderer
    private fun initUserInterface()
    {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomnavigationqr)

        // * Set BottomNavigationView Item Tinted => null
        bottomNavigationView.itemIconTintList = null

        bottomNavigationView.selectedItemId = R.id.qr

        // * Set On BottomNavigationView Item Clicked Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    // * Bom Navigation Bar
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId)
            {
                R.id.home ->
                {
                    val home = Intent(this, MainActivity::class.java)
                    startActivity(home)
                    cameraSource.stop()
                    this.finish()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.search ->
                {
                    val search = Intent(this, SearchActivity::class.java)
                    startActivity(search)
                    cameraSource.stop()
                    this.finish()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.qr ->
                {
                    return@OnNavigationItemSelectedListener true
                }

                R.id.profile ->
                {
                    val profile = Intent(this, ProfileActivity::class.java)
                    startActivity(profile)
                    cameraSource.stop()
                    this.finish()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    )
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == SCANNER_CODE && grantResults.isNotEmpty())
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                controlSetup()
            }
            else
            {
                Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    private val surfaceCallBack = object : SurfaceHolder.Callback
    {
        override fun surfaceCreated(holder: SurfaceHolder)
        {
            println(">>>>")
        }

        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int)
        {
            try
            {
                cameraSource.start(holder)
            }
            catch (ex: Exception)
            {
                Toast.makeText(applicationContext, ex.toString(), Toast.LENGTH_LONG).show()
            }
        }

        override fun surfaceDestroyed(holder: SurfaceHolder)
        {
            cameraSource.stop()
        }

    }

    private val processor = object : Detector.Processor<Barcode>
    {
        override fun release()
        {
            println("????")
        }

        override fun receiveDetections(p0: Detector.Detections<Barcode>)
        {
            if (p0.detectedItems.isNotEmpty() && p0 != null)
            {
                val qrCodes: SparseArray<Barcode> = p0.detectedItems
                val code = qrCodes.valueAt(0)

                value = code.displayValue

                println(""">>>>>> ???????? $value""")

                parseValue(value)


            }
            else
            {
                Log.e(TAG, "receiveDetections: ?????????????>")
            }

        }

    }

    private fun parseValue(@NonNull scannedValue: String)
    {
        try
        {
            val parsingValue = Intent(this, QrViewActivity::class.java)
            parsingValue.putExtra("value", scannedValue)
            parsingValue.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivityForResult(parsingValue, 2)
            this@QRActivity.finish()
        }
        catch (ex: Exception)
        {
            throw ex
        }
    }
}