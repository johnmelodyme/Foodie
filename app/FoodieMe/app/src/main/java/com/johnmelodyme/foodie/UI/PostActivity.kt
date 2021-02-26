package com.johnmelodyme.foodie.UI

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.johnmelodyme.foodie.Database.DatabaseHelper
import com.johnmelodyme.foodie.Database.SqlQuery
import com.johnmelodyme.foodie.R
import java.io.FileNotFoundException
import java.io.InputStream

class PostActivity : AppCompatActivity()
{
    private var REQUEST_ID = 999

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

    }

    override fun finish()
    {
        super.finish()
        this.overridePendingTransition(0, R.anim.fade_out)
    }


    override fun onBackPressed()
    {
        this.showDialogOnBackPressed()
    }


    private fun showDialogOnBackPressed()
    {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Are you sure you want to go back?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                val home = Intent(this@PostActivity, MainActivity::class.java)
                startActivity(home)
                finish()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()

        alert.setTitle(resources.getString(R.string.app_name) + "\t Back to Home")

        alert.show()
    }

    fun onClickPostIt(view: View)
    {
        when (view.id)
        {
            R.id.post_status ->
            {
                postStatus()
            }
        }
    }

    private fun postStatus()
    {
        val databaseHelper: DatabaseHelper = DatabaseHelper(this, "Foodie.sqlite", null, 1)

        databaseHelper.query(SqlQuery.userQuery)

        onRequestForPermission()
    }

    private fun onRequestForPermission()
    {

        ActivityCompat.requestPermissions(
            this@PostActivity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_ID
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    )
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_ID)
        {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "images/*"
                startActivityForResult(intent, REQUEST_ID)
            }
            else
            {
                Toast.makeText(
                    this,
                    "Consider To Allow me to post ? ",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        val postedImage: ImageView = findViewById<ImageView>(R.id.post_image)

        if (requestCode == REQUEST_ID && resultCode == RESULT_OK && data != null)
        {
            val uri: Uri? = data.data

            try
            {
                // TODO Revision Needed
                // setContentView(R.layout.recipe_qr)
                val inputStream: InputStream? = uri?.let { contentResolver.openInputStream(it) }
                val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
                postedImage.setImageBitmap(bitmap)

            }
            catch (file: FileNotFoundException)
            {
                file.stackTrace
            }
        }
    }
}