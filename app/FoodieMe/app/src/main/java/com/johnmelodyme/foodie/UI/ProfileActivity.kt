package com.johnmelodyme.foodie.UI

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.johnmelodyme.foodie.R
import com.johnmelodyme.foodie.UI.QR.QRActivity
import java.util.*

class ProfileActivity : AppCompatActivity()
{
    private lateinit var profilePicture: ImageView
    private lateinit var profilePictureForeground: ImageView
    private lateinit var imageUri: Uri
    private lateinit var storage: FirebaseStorage
    private lateinit var storageReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // * Initialise User Interface
        initUserInterface()

        // * Firebase Instance
        initFirebase()
    }

    private fun initFirebase()
    {
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference
    }

    // * User Interface Renderer
    private fun initUserInterface()
    {
        val userAccountName: FirebaseUser? = Firebase.auth.currentUser

        userAccountName?.let {
            val name = userAccountName.displayName
            val email = userAccountName.email
            val photoUrl = userAccountName.photoUrl
            val emailVerified = userAccountName.isEmailVerified
            val uid = userAccountName.uid
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomnavigationprofilee)

        // * Set BottomNavigationView Item Tinted => null
        bottomNavigationView.itemIconTintList = null

        bottomNavigationView.selectedItemId = R.id.profile

        // * Set On BottomNavigationView Item Clicked Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val userAccountId: TextView = findViewById(R.id.account_id)

        userAccountId.text = userAccountName?.email.toString()
    }

    override fun finish()
    {
        super.finish()
        overridePendingTransition(0, R.anim.fade_out)
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
                    this.finish()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.search ->
                {
                    val search = Intent(this, SearchActivity::class.java)
                    startActivity(search)
                    this.finish()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.qr ->
                {
                    val qr = Intent(this, QRActivity::class.java)
                    startActivity(qr)
                    this.finish()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.profile ->
                {
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    fun onClickProfile(view: View)
    {
        when (view.id)
        {
            R.id.sign_out ->
            {
                // TODO ADD SIGN OUT
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
            }

            R.id.im_profile ->
            {
                // TODO profile image
                profilePicture = findViewById<ImageView>(R.id.im_profile)
                chooseProfilePictures()
            }
        }
    }

    private fun chooseProfilePictures()
    {
        val intent_choose_img: Intent = Intent()
        intent_choose_img.type = "image/*"
        intent_choose_img.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent_choose_img, 1)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.data != null)
        {
            imageUri = data.data!!
            profilePicture.setImageURI(imageUri)
            uploadProfilePicture()
        }
    }

    private fun uploadProfilePicture()
    {
        val progress: ProgressDialog = ProgressDialog(this)
        progress.setTitle("Uploading ... ")
        progress.show()

        val randomKey: String = UUID.randomUUID().toString()
        val sReference: StorageReference = storageReference.child("images/" + randomKey)



        sReference.putFile(imageUri).addOnSuccessListener {
            progress.dismiss()

            Snackbar
                .make(findViewById(android.R.id.content), "Image Uploaded", Snackbar.LENGTH_LONG)
                .show()
        }.addOnFailureListener {
            Toast.makeText(applicationContext, "Failed TO Upload", Toast.LENGTH_SHORT).show()
        }
    }
}