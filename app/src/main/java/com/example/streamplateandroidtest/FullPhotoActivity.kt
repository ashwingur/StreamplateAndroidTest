package com.example.streamplateandroidtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.streamplateandroidtest.databinding.ActivityFullPhotoBinding
import com.squareup.picasso.Picasso

class FullPhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Photo"

        initViews()
    }

    fun initViews(){
        binding.imageNameTv.text = intent.extras!!.getString(FullPhotoActivityConstants.EXTRA_NAME).toString()
        Picasso.get()
            .load(intent.extras!!.getString(FullPhotoActivityConstants.EXTRA_IMG_URL))
            .into(binding.fullsizeIv)
    }
}

object FullPhotoActivityConstants {
    const val EXTRA_NAME = "com.example.streamplateandroidtest.EXTRA_NAME"
    const val EXTRA_IMG_URL = "com.example.streamplateandroidtest.IMG_URL"
}