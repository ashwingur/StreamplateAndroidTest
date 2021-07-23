package com.example.streamplateandroidtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.streamplateandroidtest.databinding.ActivityPhotosBinding
import com.example.streamplateandroidtest.viewmodels.PhotosViewModel
import com.example.streamplateandroidtest.viewmodels.UserListViewModel

class PhotosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotosBinding
    lateinit var viewModel: PhotosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        viewModel.setPhotoId(intent.extras!!.getInt(PhotosActivityConstants.EXTRA_ID))

    }

    fun initViewModel(){
        viewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
        viewModel.photos.observe(this, Observer {
            for (photo in it){
                println(photo)
            }
        })
    }
}

object PhotosActivityConstants {
    const val EXTRA_ID = "com.example.streamplateandroidtest.EXTRA_ID"
}