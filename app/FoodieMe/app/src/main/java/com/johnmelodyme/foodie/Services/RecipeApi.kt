package com.johnmelodyme.foodie.Services

import com.johnmelodyme.foodie.Constant.ConstantValue
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = ConstantValue.basedUrlRecipe
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL)
        .build()

interface RecipeApi
{
    @GET("recipe")
    fun getAllData(): Call<List<Property>>
}

object Api
{
    val retrofitService: RecipeApi by lazy { retrofit.create(RecipeApi::class.java) }
}