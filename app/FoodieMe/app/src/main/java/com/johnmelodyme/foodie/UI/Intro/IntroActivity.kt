package com.johnmelodyme.foodie.UI.Intro

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.johnmelodyme.foodie.R

class IntroActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager

    var intArray: IntArray = intArrayOf(
        R.layout.first_slide,
        R.layout.second_slide,
        R.layout.third_slide
    )

    lateinit var linearLayout: LinearLayout

    lateinit var dots: Array<ImageView>

    lateinit var pageAdapter: PageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        viewPager = findViewById(R.id.viewPage)
        pageAdapter = PageAdapter(intArray, this)
        viewPager.adapter = pageAdapter
        linearLayout = findViewById<LinearLayout>(R.id.dots)
    }

//    fun RenderDots(position: Int) {
//        if (linearLayout != null) {
//            linearLayout.removeAllViews()
//        }
//
//        dots = Array(intArray.size, (i -> ImageView(this)))
//        for (i in 0..intArray.size - 1) {
//
//        }
//    }
}