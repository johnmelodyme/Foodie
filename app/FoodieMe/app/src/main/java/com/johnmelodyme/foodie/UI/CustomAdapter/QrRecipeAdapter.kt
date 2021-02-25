package com.johnmelodyme.foodie.UI.CustomAdapter
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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.johnmelodyme.foodie.Model.RecipeModelQR
import com.johnmelodyme.foodie.R

class QrRecipeAdapter(context: Context, arrayListDetails: ArrayList<RecipeModelQR>) : BaseAdapter()
{
    private val layoutInflater: LayoutInflater
    private val arrayListDetails: ArrayList<RecipeModelQR>

    init
    {
        this.layoutInflater = LayoutInflater.from(context)
        this.arrayListDetails = arrayListDetails
    }

    override fun getCount(): Int
    {
        return arrayListDetails.size
    }

    override fun getItem(position: Int): Any
    {
        return arrayListDetails.get(position)
    }

    override fun getItemId(position: Int): Long
    {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View?
    {

        val view: View?
        val listRowHolder: QRRecipeHolder

        if (convertView == null)
        {
            view = this.layoutInflater.inflate(R.layout.recipe_qr, parent, false)
            listRowHolder = QRRecipeHolder(view)
            view.tag = listRowHolder
        }
        else
        {
            view = convertView
            listRowHolder = view.tag as QRRecipeHolder
        }

        view?.context?.let {
            Glide.with(it).load(arrayListDetails.get(position).imageUrl).centerCrop()
                .into(listRowHolder.image)
        }
        listRowHolder.recipeName.text = arrayListDetails.get(position).recipeName
        listRowHolder.ingredient.text = arrayListDetails.get(position).ingredient
        listRowHolder.description.text = arrayListDetails.get(position).description
        listRowHolder.data.text = arrayListDetails.get(position).data

        return view
    }
}