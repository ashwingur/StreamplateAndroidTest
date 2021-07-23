package com.example.streamplateandroidtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.streamplateandroidtest.databinding.ActivityPhotosBinding

class PhotosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        println(intent.extras!!.getInt(PhotosActivityConstants.EXTRA_ID))

    }
}

object PhotosActivityConstants {
    const val EXTRA_ID = "com.example.streamplateandroidtest.EXTRA_ID"
}