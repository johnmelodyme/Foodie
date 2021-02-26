package com.johnmelodyme.foodie.UI

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.johnmelodyme.foodie.Database.DatabaseHelper
import com.johnmelodyme.foodie.Database.SqlQuery
import com.johnmelodyme.foodie.Model.Posts
import com.johnmelodyme.foodie.R
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream
import java.lang.Exception

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
        try
        {
            val databaseHelper: DatabaseHelper = DatabaseHelper(this, "Foodie.sqlite", null, 1)
            val sqliteDatabase: SQLiteDatabase = databaseHelper.writeableDatabase
            sqliteDatabase.execSQL("CREATE TABLE Foodie(id INTEGER PRIMARY KEY, Post TEXT, Image BLOB)")

        }
        catch (ex: Exception)
        {
            ex.stackTrace
        }

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
                startActivityForResult(intent, 999)
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

    private fun passData(context: Context, @NonNull post: Posts): Intent
    {

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("value", post)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent)
        this.finish()

        return intent
    }

    private fun parseValue(context: Context, @NonNull post: ArrayList<Posts>): Intent
    {

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("value", post)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent)
        this.finish()


        return intent
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        val textView: TextView = findViewById<TextView>(R.id.result)
        val newPost: EditText = findViewById<EditText>(R.id.new_post)
        val postedImage: ImageView = findViewById<ImageView>(R.id.post_image)
        val databaseHelper: DatabaseHelper = DatabaseHelper(this, "Foodie.sqlite", null, 1)
        val sqliteDatabase: SQLiteDatabase = databaseHelper.writeableDatabase
        val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val randomWord: String = List((1..10).random()) { alphabet.random() }.joinToString("")

        if (requestCode == REQUEST_ID && resultCode == RESULT_OK && data != null)
        {
            val uri: Uri? = data.data

            try
            {
                // TODO Revision Needed
                // setContentView(R.layout.recipe_qr)
                // val inputStream: InputStream? = uri?.let { contentResolver.openInputStream(it) }
                // val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
                // postedImage.setImageBitmap(bitmap)
                val postsUser: ArrayList<Posts> = ArrayList<Posts>()
                val userPost: String = newPost.text.toString()
                val stringFilePath: String =
                    Environment.getDownloadCacheDirectory().path + "/Download/" + randomWord + ".jpeg"
                val bitmap: Bitmap = BitmapFactory.decodeFile(stringFilePath)
                val byteArrayOutputStream: ByteArrayOutputStream = ByteArrayOutputStream()

                bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream)

                val byteImage: ByteArray = byteArrayOutputStream.toByteArray()
                val contentValues: ContentValues = ContentValues()
                contentValues.put("Name", userPost)
                contentValues.put("Image", byteImage)

                sqliteDatabase.insert("Foodie", null, contentValues)
                textView.text = "Successful"

                postsUser.add(Posts(userPost, ""))


                parseValue(this, postsUser)
            }
            catch (file: FileNotFoundException)
            {
                file.stackTrace
            }
        }
    }
}