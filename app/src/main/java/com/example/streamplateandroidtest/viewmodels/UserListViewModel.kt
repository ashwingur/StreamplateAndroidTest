package com.example.streamplateandroidtest.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.streamplateandroidtest.models.User
import com.example.streamplateandroidtest.repositories.UserRepository

class UserListViewModel(application: Application) : AndroidViewModel(application) {
    // No need to make this a singleton because it will be bound to a single activity

    val users: LiveData<List<User>> = UserRepository.getUsers(application)

    fun cancelJobs(){
        UserRepository.cancelJobs()

    }
}