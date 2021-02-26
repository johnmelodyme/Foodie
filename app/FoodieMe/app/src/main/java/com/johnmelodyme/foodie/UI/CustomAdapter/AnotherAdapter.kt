package com.johnmelodyme.foodie.UI.CustomAdapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import com.johnmelodyme.foodie.R

class AnotherAdapter(
    @NonNull private val context: Activity,
    private val title: Array<String>,
    private val description: Array<String>,
    private val imgID: Array<Int>
) : ArrayAdapter<String>(context, R.layout.recipe_qr, title)
{
    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        val inflater: LayoutInflater = context.layoutInflater
        val rowView: View = inflater.inflate(R.layout.main_post, null, true)

        val imageView: ImageView = rowView.findViewById(R.id.image_post)
        val descriptionText: TextView = rowView.findViewById(R.id.text_posts)

        // TODO FIX THIS
//        titleText.text = title[position]
//        imageView.setImageResource(imgID[position])
//        descriptionText.text = description[position]


        return rowView
    }
}
