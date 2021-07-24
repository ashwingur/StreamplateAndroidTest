package com.example.streamplateandroidtest.api

import com.example.streamplateandroidtest.models.Photo
import com.example.streamplateandroidtest.models.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    // Gets the list of users
    // It is a suspend function because we will be using it in a coroutine. It can be paused, cancelled etc.
    @GET("users")
    suspend fun getUsers() : Response<List<User>>

    // Gets all the photos
    @GET("photos")
    suspend fun getPhotos() : Response<List<Photo>>
}