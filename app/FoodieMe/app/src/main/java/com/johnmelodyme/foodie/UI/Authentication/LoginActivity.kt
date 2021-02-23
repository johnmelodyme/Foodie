package com.johnmelodyme.foodie.UI.Authentication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.johnmelodyme.foodie.R
import com.johnmelodyme.foodie.UI.MainActivity

class LoginActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onClickLogin(view: View)
    {
        when (view.id)
        {
            R.id.login ->
            {

            }

            R.id.reset_user ->
            {
                onReset(view)
            }
        }
    }

    private fun onReset(view: View?)
    {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Do you Really Want To Reset Password?")
            .setCancelable(false)
            .setPositiveButton("Proceed") { dialog, id ->
                var authentication: FirebaseAuth? = null
                authentication = FirebaseAuth.getInstance()
                val user: String = authentication.currentUser?.email.toString()

                authentication.sendPasswordResetEmail(user).addOnCompleteListener { task ->
                    if (task.isSuccessful)
                    {
                        val home = Intent(this, MainActivity::class.java)
                        this.startActivity(home)

                        Toast.makeText(
                            this@LoginActivity,
                            "An Password Reset Had Sent To Your Email",
                            Toast.LENGTH_LONG
                        ).show()

                        this.finish()
                    }
                }
            }
            .setNegativeButton("Cancel") { dialog, id ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()

        alert.setTitle(resources.getString(R.string.app_name) + "\tReset Password?")

        alert.show()

    }
}