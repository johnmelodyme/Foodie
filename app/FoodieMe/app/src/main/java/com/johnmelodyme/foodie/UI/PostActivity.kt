package com.johnmelodyme.foodie.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.johnmelodyme.foodie.R

class PostActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

    }

    override fun finish()
    {
        super.finish()
        this.overridePendingTransition(0, R.anim.fade_out)
    }


    override fun onBackPressed()
    {
        this.showDialogOnBackPressed()
    }


    private fun showDialogOnBackPressed()
    {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Are you sure you want to go back?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                val home = Intent(this@PostActivity, MainActivity::class.java)
                startActivity(home)
                finish()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()

        alert.setTitle(resources.getString(R.string.app_name) + "\tBack to Home")

        alert.show()
    }
}