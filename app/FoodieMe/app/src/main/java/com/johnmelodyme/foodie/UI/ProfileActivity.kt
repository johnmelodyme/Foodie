package com.johnmelodyme.foodie.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.johnmelodyme.foodie.R
import com.johnmelodyme.foodie.UI.QR.QRActivity

class ProfileActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // * Initialise User Interface
        initUserInterface()
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
            }
        }
    }
}