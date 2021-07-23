package com.example.userinfolistactivity.api

import com.example.userinfolistactivity.models.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers() : User
}