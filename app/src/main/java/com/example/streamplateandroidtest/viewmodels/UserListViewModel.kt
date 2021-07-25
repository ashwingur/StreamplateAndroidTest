package com.example.streamplateandroidtest.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.streamplateandroidtest.models.User
import com.example.streamplateandroidtest.repositories.UserRepository

class UserListViewModel(application: Application) : AndroidViewModel(application) {
    // No need to make this a singleton because it will be bound to a single activity
    // Using AndroidViewModel so we can pass in the context as well to the repository,
    // which lets us create a toast message if an error occurred in the request

    val users: LiveData<List<User>> = UserRepository.getUsers(application)

    fun cancelJobs(){
        // In case this is required, the call can be cancelled (for example if it is taking too long)
        UserRepository.cancelJobs()

    }
}