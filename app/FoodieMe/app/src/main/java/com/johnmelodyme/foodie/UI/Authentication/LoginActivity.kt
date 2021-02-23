package com.johnmelodyme.foodie.UI.Authentication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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
        val userEmail: EditText = findViewById(R.id.user_email_log)
        val userPassword: EditText = findViewById(R.id.user_password_log)

        when (view.id)
        {
            R.id.login ->
            {
                when
                {
                    TextUtils.isEmpty(userEmail.text.toString().trim { it <= ' ' })    ->
                    {
                        userEmail.error = "Please Enter Your Email ?"
                    }

                    TextUtils.isEmpty(userPassword.text.toString().trim { it <= ' ' }) ->
                    {
                        userPassword.error = "Please Enter Your Password ?"
                    }

                    else                                                               ->
                    {
                        val email: String = userEmail.text.toString().trim() { it <= ' ' }
                        val password: String = userPassword.text.toString().trim() { it <= ' ' }

                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful)
                                {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Login Successful",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent = Intent(this, MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    this.finish()
                                }
                                else
                                {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Login Failed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    }
                }
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

        alert.setTitle(resources.getString(R.string.app_name) + "\t Reset Password?")

        alert.show()

    }
}