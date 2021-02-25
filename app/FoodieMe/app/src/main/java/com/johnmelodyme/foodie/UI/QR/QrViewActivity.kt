package com.johnmelodyme.foodie.UI.QR

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
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.vision.CameraSource
import com.johnmelodyme.foodie.Constant.ConstantValue
import com.johnmelodyme.foodie.Model.RecipeModelQR
import com.johnmelodyme.foodie.R
import com.johnmelodyme.foodie.Services.Api
import com.johnmelodyme.foodie.Services.Property
import com.johnmelodyme.foodie.UI.CustomAdapter.QrRecipeAdapter
import com.johnmelodyme.foodie.UI.MainActivity
import retrofit2.Call
import retrofit2.Response

class QrViewActivity : AppCompatActivity()
{
    private lateinit var result: String
    private lateinit var URL: String
    private lateinit var parsedValue: Bundle
    private lateinit var cameraSource: CameraSource
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    private var arrayList: ArrayList<RecipeModelQR> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_view)

        URL = ConstantValue.basedUrlRecipe

        // * Render User Interface
        initUi(savedInstanceState)

        getAllData(savedInstanceState)
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
        overridePendingTransition(0, R.anim.fade_out)
        val home = Intent(this, MainActivity::class.java)
        startActivity(home)
    }

    private fun initUi(bundle: Bundle?)
    {
        parsedValue = intent.extras!!
        result = parsedValue.getString("value").toString()

        manager = LinearLayoutManager(this)

        println(result)
    }

    private fun getAllData(bundle: Bundle?)
    {
        Api.retrofitService.getAllData().enqueue(object : retrofit2.Callback<List<Property>>
        {
            override fun onResponse(call: Call<List<Property>>, response: Response<List<Property>>)
            {
                if (response.isSuccessful)
                {
                    recyclerView = findViewById<RecyclerView>(R.id.listviewrecipe).apply {
                        myAdapter = QrRecipeAdapter(response.body()!!)
                        layoutManager = manager
                        adapter = myAdapter
                    }
                }
                else
                {
                    recyclerView = findViewById<RecyclerView>(R.id.listviewrecipe).apply {
                        myAdapter = QrRecipeAdapter(response.body()!!)
                        layoutManager = manager
                        adapter = myAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Property>>, t: Throwable)
            {
                t.printStackTrace()
            }
        })
    }
}