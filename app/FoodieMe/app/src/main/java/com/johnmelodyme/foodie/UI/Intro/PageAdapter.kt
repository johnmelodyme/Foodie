package com.johnmelodyme.foodie.UI.Intro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class PageAdapter : PagerAdapter {

    lateinit var layouts: IntArray
    lateinit var inflate: LayoutInflater
    lateinit var con: Context

    constructor(layouts: IntArray, con: Context) : super() {
        this.layouts = layouts
        this.con = con
        inflate = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return layouts.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        var view: View = `object` as View
        container!!.removeView(view)

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view: View = inflate.inflate(layouts[position], container, false)
        container!!.addView(view)
        return view
    }
}