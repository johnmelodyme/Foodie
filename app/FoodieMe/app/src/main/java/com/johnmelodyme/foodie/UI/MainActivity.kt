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
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.johnmelodyme.foodie.Constant.ConstantValue
import com.johnmelodyme.foodie.Functions.Methods
import com.johnmelodyme.foodie.R
import com.johnmelodyme.foodie.UI.Authentication.RegistrationActivity
import com.johnmelodyme.foodie.UI.CustomAdapter.AnotherAdapter
import com.johnmelodyme.foodie.UI.CustomAdapter.MainAdapter
import com.johnmelodyme.foodie.UI.CustomAdapter.QrRecipeAdapter
import com.johnmelodyme.foodie.UI.CustomAdapter.SearchActivityAdapter
import com.johnmelodyme.foodie.UI.QR.QRActivity
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt

class MainActivity : AppCompatActivity()
{
    private val TAG = ConstantValue.AppName

    override fun onStart()
    {
        super.onStart()
        Methods.log()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // * Disable Dark Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // * Initialise User Interface
        initUserInterface()

        // * Request For User Permission
        onRequestForUserPermission(savedInstanceState)

        // * Initialise Firebase
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

        val mFab = findViewById<FloatingActionButton>(R.id.fab)
        mFab.setOnClickListener {
            val intentToPost = Intent(this, PostActivity::class.java)
            startActivity(intentToPost)
            this.finish()
        }


        showPost(savedInstanceState)
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

    private fun onRequestForUserPermission(bundle: Bundle?)
    {
        if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.CAMERA
                ) !=
                PackageManager.PERMISSION_GRANTED

        )
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this@MainActivity,
                        Manifest.permission.CAMERA
                    )
            )
            {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.CAMERA), 1
                )
            }
            else
            {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.CAMERA), 1
                )
            }
        }
    }

    private fun showPost(bundle: Bundle?)
    {
        val listView: ListView = findViewById(R.id.recyclerviewmain)
//        val recyclerView: RecyclerView = findViewById(R.id.recyclerviewmain)
//        val data: Array<out Parcelable>? = intent.getParcelableArrayExtra("value")
//        val adapter = MainAdapter(data)
//        val adapter = SearchActivityAdapter(data)
//        recyclerView.adapter = adapter

        val categories =
            arrayOf<String>("Desserts", "Western", "Local", "", "", "", "", "", "", "", "")
        val imageId = arrayOf<Int>(
            R.drawable.abc,
            R.drawable.sandwich,
            R.drawable.ic_local
        )

        val listAdapter = AnotherAdapter(this, categories, emptyArray(), imageId)
        listView.adapter = listAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val itemAtPos = parent.getItemAtPosition(position)
            val itemIdAtPos = parent.getItemIdAtPosition(position)

            Toast.makeText(
                this,
                "Click on item at $itemAtPos its item id $itemIdAtPos",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}