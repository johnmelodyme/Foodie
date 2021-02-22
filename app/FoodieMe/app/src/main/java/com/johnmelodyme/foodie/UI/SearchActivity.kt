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
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.johnmelodyme.foodie.R

class SearchActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // * Initialise User Interface
        initUserInterface()
    }

    // * User Interface Renderer
    private fun initUserInterface()
    {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomnavigationsearch)

        // * Set BottomNavigationView Item Tinted => null
        bottomNavigationView.itemIconTintList = null

        bottomNavigationView.selectedItemId = R.id.search

        // * Set On BottomNavigationView Item Clicked Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun finish() {
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
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }
}