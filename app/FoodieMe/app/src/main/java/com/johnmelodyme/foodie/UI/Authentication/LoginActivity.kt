package com.johnmelodyme.foodie.UI.Authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.johnmelodyme.foodie.R

class LoginActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onClickLogin(view: View)
    {
        when(view.id)
        {
            R.id.login ->
            {

            }
        }
    }
}