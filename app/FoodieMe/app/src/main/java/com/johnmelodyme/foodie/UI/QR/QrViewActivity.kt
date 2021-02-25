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
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import com.google.android.gms.vision.CameraSource
import com.johnmelodyme.foodie.Constant.ConstantValue
import com.johnmelodyme.foodie.Model.RecipeModelQR
import com.johnmelodyme.foodie.R
import com.johnmelodyme.foodie.UI.CustomAdapter.QrRecipeAdapter
import com.johnmelodyme.foodie.UI.MainActivity
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class QrViewActivity : AppCompatActivity()
{
    private lateinit var result: String
    private lateinit var URL: String
    private lateinit var parsedValue: Bundle
    private lateinit var cameraSource: CameraSource
    private lateinit var listView: ListView
    private var arrayList: ArrayList<RecipeModelQR> = ArrayList()
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_view)

        URL = ConstantValue.basedUrlRecipe

        // * Render User Interface
        initUi(savedInstanceState, URL)
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

    private fun initUi(bundle: Bundle?, _url: String)
    {
        parsedValue = intent.extras!!
        result = parsedValue.getString("value").toString()

        println(result)

        run(_url)

    }

    private fun run(url: String)
    {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback
        {
            override fun onFailure(call: Call, e: IOException)
            {
                throw e
            }

            override fun onResponse(call: Call, response: Response)
            {
                val string_response: String = response.body?.string().toString()
                val json_contact: JSONObject = JSONObject(string_response)
                val jsonarray_info: JSONArray = json_contact.getJSONArray("recipe")

                var i: Int = 0
                val size: Int = jsonarray_info.length()

                arrayList = ArrayList();
                listView = findViewById(R.id.listviewrecipe)

                for (i: Int in 0..size - 1)
                {
                    val json_objectdetail: JSONObject = jsonarray_info.getJSONObject(i)
                    val model: RecipeModelQR = RecipeModelQR()

                    model.imageUrl = json_objectdetail.getString("image_url")
                    model.recipeName = json_objectdetail.getString("recipe_name")
                    model.ingredient = json_objectdetail.getString("ingredient")
                    model.description = json_objectdetail.getString("description")
                    model.data = json_objectdetail.getString("data")

                    arrayList.add(model)
                }

                runOnUiThread {
                    val obj_adapter: QrRecipeAdapter
                    obj_adapter = QrRecipeAdapter(applicationContext, arrayList)
                    listView.adapter = obj_adapter
                }
            }
        })
    }

}