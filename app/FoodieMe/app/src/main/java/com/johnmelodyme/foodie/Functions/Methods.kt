package com.johnmelodyme.foodie.Functions

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.johnmelodyme.foodie.Constant.ConstantValue
import com.johnmelodyme.foodie.UI.MainActivity

class Methods
{
    private val TAG = ConstantValue.AppName

    companion object
    {
        /**
         * @param bundle:
         * return AppCompatActivity to perform a precise Singleton.
         * @param context:
         * To be declare of the specific STATE Activity's State
         *
         * <p>Refer: https://stackoverflow.com/a/66273778/10758321</p>
         */
        @Suppress("DEPRECATION")
        fun handlingSplash(bundle: Bundle?, context: Context)
        {
            Log.i(TAG, "handlingSplash: ")
            Handler().postDelayed({
                context.startActivity(Intent(context, MainActivity::class.java))
            }, ConstantValue.MathematicalValue[0].toLong())
        }


    }
}