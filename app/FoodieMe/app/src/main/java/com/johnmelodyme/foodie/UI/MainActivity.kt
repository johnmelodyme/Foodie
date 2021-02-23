package com.johnmelodyme.foodie.UI

/**
 * Copyright © 2021 by John Melody Me
 * <p>
 * All rights reserved. No part of this software may be reproduced,
 * distributed, or transmitted in any form or by any means, including
 * photocopying, recording, or other electronic or mechanical methods,
 * without the prior written permission of the developer, except in the
 * case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. For permission requests,
 * write to the code-owner, addressed “Attention: Permissions Coordinator,”
 * at the address below.
 * <p>
 * https://johnmelodyme.github.io/
 */
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.johnmelodyme.foodie.Constant.ConstantValue
import com.johnmelodyme.foodie.Functions.Methods
import com.johnmelodyme.foodie.R
import com.johnmelodyme.foodie.UI.Authentication.RegistrationActivity

class MainActivity : AppCompatActivity()
{
    private val TAG = ConstantValue.AppName

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // * Initialise User Interface
        initUserInterface()

        FirebaseApp.initializeApp(this)

        val currentUser = Firebase.auth.currentUser

        if (currentUser != null)
        {
            ConstantValue.isUserFirstTime = false
            Log.d(TAG, "User Existed")
        }
        else
        {
            Log.d(TAG, "New User Detected")
            val intentToRegister = Intent(this, RegistrationActivity::class.java)
            startActivity(intentToRegister)
            finish()
        }
    }

    // * User Interface Renderer
    private fun initUserInterface()
    {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomnavigationhome)

        // * Set BottomNavigationView Item Tinted => null
        bottomNavigationView.itemIconTintList = null

        bottomNavigationView.selectedItemId = R.id.home

        // * Set On BottomNavigationView Item Clicked Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

    }

    override fun finish()
    {
        super.finish()
        overridePendingTransition(0, R.anim.fade_out)
    }

    // * Bom Navigation Bar
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item
            ->
            when (item.itemId)
            {
                R.id.home ->
                {
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
                    val profile = Intent(this, ProfileActivity::class.java)
                    startActivity(profile)
                    this.finish()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}