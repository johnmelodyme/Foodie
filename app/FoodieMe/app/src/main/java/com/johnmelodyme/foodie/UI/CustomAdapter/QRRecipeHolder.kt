package com.johnmelodyme.foodie.UI.CustomAdapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.johnmelodyme.foodie.R

class QRRecipeHolder(row: View?) : RecyclerView.ViewHolder(row!!), View.OnClickListener
{

//    private var view: View = v
//    private var photo: Photo? = null

    override fun onClick(v: View?)
    {
        println("CLick Click")
    }

    val image: ImageView = row?.findViewById(R.id.image_recipe) as ImageView
    val recipeName: TextView = row?.findViewById(R.id.name_of_recipe) as TextView
    val ingredient: TextView = row?.findViewById(R.id.ingredient_recipe) as TextView
    val description: TextView = row?.findViewById(R.id.description_recipe) as TextView
    val data: TextView = row?.findViewById(R.id.recipe_data) as TextView

}