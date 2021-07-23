package com.example.userinfolistactivity.api

import com.example.userinfolistactivity.models.User
import retrofit2.http.GET

interface ApiService {

    // Gets the list of users
    @GET("users")
    suspend fun getUsers() : List<User>
}