package com.johnmelodyme.foodie.Functions

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.johnmelodyme.foodie.Constant.ConstantValue
import com.johnmelodyme.foodie.R
import com.johnmelodyme.foodie.UI.MainActivity

class Methods {
    private val TAG = ConstantValue.AppName

    companion object {
        /**
         * @param bundle:
         * return AppCompatActivity to perform a precise Singleton.
         * @param context:
         * To be declare of the specific STATE Activity's State
         *
         * <p>Refer: https://stackoverflow.com/a/66273778/10758321</p>
         */
        @Suppress("DEPRECATION")
        fun handlingSplash(bundle: Bundle?, @NonNull context: Context) {
            Log.i(TAG, "handlingSplash: ")
            Handler().postDelayed({
                context.startActivity(Intent(context, MainActivity::class.java))
            }, ConstantValue.MathematicalValue[0].toLong())
        }

        fun userVerification() {
            val currentUser = Firebase.auth.currentUser

            if (currentUser != null) {
                ConstantValue.isUserFirstTime = false
            }
        }

        fun log() {
            println("I am so poor that I dont have $$ to get a droplet or pay for firebase for the project")
        }
    }
}