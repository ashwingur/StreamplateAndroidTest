package com.example.userinfolistactivity.api

import com.example.userinfolistactivity.models.Photo
import com.example.userinfolistactivity.models.User
import retrofit2.http.GET

interface ApiService {

    // Gets the list of users
    // It is a suspend function because we will be using it in a coroutine. It can be paused, cancelled etc.
    @GET("users")
    suspend fun getUsers() : List<User>

    // Gets all the photos
    @GET("photos")
    suspend fun getPhotos() : List<Photo>
}