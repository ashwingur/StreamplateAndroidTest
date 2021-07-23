package com.example.userinfolistactivity.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitBuilder {

    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    // It is lazy because we only want a single retrofit builder instance
    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    // Singleton api service which will be used to make the network request
    val apiService: ApiService by lazy {
        retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }


}