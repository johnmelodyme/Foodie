package com.johnmelodyme.foodie.UI.CustomAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.johnmelodyme.foodie.R
import com.johnmelodyme.foodie.Services.Property

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


class QrRecipeAdapter(private val data: List<Property>) :
    RecyclerView.Adapter<QrRecipeAdapter.QRViewHolder>()
{
    class QRViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    {
        fun bind(property: Property)
        {
            val imageView = view.findViewById<ImageView>(R.id.image_recipe)
            val name = view.findViewById<TextView>(R.id.name_of_recipe)
            val ingredient = view.findViewById<TextView>(R.id.ingredient_recipe)
            val description = view.findViewById<TextView>(R.id.description_recipe)
            val data = view.findViewById<TextView>(R.id.recipe_data)

            Glide.with(view.context).load(property.image).centerCrop().into(imageView)

            name.text = property.name
            ingredient.text = property.ingredient
            description.text = property.description
            data.text = property.data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QRViewHolder
    {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recipe_qr, parent, false)
        return QRViewHolder(v)
    }

    override fun onBindViewHolder(holder: QRViewHolder, position: Int)
    {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int
    {
        return data.size
    }
}