package com.johnmelodyme.foodie.UI.CustomAdapter

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.johnmelodyme.foodie.Model.Posts
import com.johnmelodyme.foodie.R

class MainAdapter(private val post: Array<out Parcelable>?) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>()
{

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {

        fun bindItems(userPosts: Posts)
        {
            val post: TextView = itemView.findViewById(R.id.text_posts)
            val imagePost: ImageView = itemView.findViewById(R.id.image_post)
            post.text = userPosts.post
            // imagePost.
            // TODO REVISION
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder
    {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.main_post, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bindItems(post?.get(position) as Posts)
    }

    override fun getItemCount(): Int
    {
        return post?.size!!
    }

}