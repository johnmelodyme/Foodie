package com.johnmelodyme.foodie.UI.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.johnmelodyme.foodie.Constant.ConstantValue
import com.johnmelodyme.foodie.R
import com.johnmelodyme.foodie.UI.MainActivity

class RegistrationActivity : AppCompatActivity()
{
    private val TAG = ConstantValue.AppName

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

    }

    fun onClickRegister(view: View)
    {
        val userEmail: EditText = findViewById(R.id.user_email_reg)
        val userPassword: EditText = findViewById(R.id.user_password_reg)

        when (view.id)
        {
            R.id.register ->
            {
                when
                {

                    TextUtils.isEmpty(userEmail.text.toString().trim { it <= ' ' }) ->
                    {
                        Toast.makeText(
                            this@RegistrationActivity,
                            "Please Enter Your Email ?",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    TextUtils.isEmpty(userPassword.text.toString().trim { it <= ' ' }) ->
                    {
                        Toast.makeText(
                            this@RegistrationActivity,
                            "Please Enter Your Password ?",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    else ->
                    {
                        val email: String = userEmail.text.toString().trim() { it <= ' ' }
                        val password: String = userPassword.text.toString().trim() { it <= ' ' }

                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(
                                OnCompleteListener
                                { task ->

                                    // * If User Registration Is Successful
                                    if (task.isSuccessful)
                                    {
                                        val firebaseUser: FirebaseUser = task.result!!.user!!

                                        Toast.makeText(
                                            this@RegistrationActivity,
                                            "Registration Successful",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        Log.i(TAG, "Register user" + firebaseUser.uid + "Successful")

                                        // * Navigate To MainActivity
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
                                        Log.e(TAG, "onClickRegister: ")
                                        Toast.makeText(
                                            this@RegistrationActivity,
                                            "Registration Failed",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            )
                    }
                }

            }

            R.id.exisitinguser ->
            {
                val home = Intent(this, MainActivity::class.java)
                startActivity(home)
                this.finish()
            }
        }
    }

    override fun finish()
    {
        super.finish()
        overridePendingTransition(0, R.anim.fade_out)
    }

    override fun onBackPressed()
    {
        super.onBackPressed()
        val intentToRegister = Intent(this, RegistrationActivity::class.java)
        startActivity(intentToRegister)
        this.finish()


    }
}