package com.johnmelodyme.foodie.Model

import android.os.Parcelable
import androidx.annotation.NonNull
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Posts(@NonNull val post: String, val image: String) : Parcelable
