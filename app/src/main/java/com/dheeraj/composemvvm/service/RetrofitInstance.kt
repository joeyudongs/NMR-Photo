package com.dheeraj.composemvvm.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val creditCardService: CreditCardService by lazy {
        retrofit.create(CreditCardService::class.java)
    }
}
