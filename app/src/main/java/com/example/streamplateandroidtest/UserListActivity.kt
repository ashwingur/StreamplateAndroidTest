package com.example.streamplateandroidtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streamplateandroidtest.viewmodels.UserListViewModel

class UserListActivity : AppCompatActivity() {

    lateinit var viewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        println("CHANGEDDDDDDDDDDDDDDDDDDDDDDDDD")
        viewModel.users.observe(this, Observer {
            println("DEBUG: ${it}")
        })
    }
}