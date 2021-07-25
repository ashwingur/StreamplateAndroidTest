package com.example.streamplateandroidtest

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.streamplateandroidtest.adapters.PhotosAdapter
import com.example.streamplateandroidtest.adapters.PhotosItemDecoration
import com.example.streamplateandroidtest.databinding.ActivityPhotosBinding
import com.example.streamplateandroidtest.viewmodels.PhotosViewModel
import com.example.streamplateandroidtest.utils.*

class PhotosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotosBinding
    lateinit var viewModel: PhotosViewModel
    private val adapter: PhotosAdapter = PhotosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Photos"


        initViewModel()
        initRecyclerView()
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
        viewModel.photos.observe(this, Observer {
            adapter.photos = it
            adapter.notifyDataSetChanged()
        })
    }

    private fun initRecyclerView(){
        binding.photosRv.layoutManager = GridLayoutManager(this
            , calculateNoOfColumns(this, PhotosActivityConstants.THUMBNAIL_SIZE) - 1) // -1 to prevent photos being cropped
        binding.photosRv.addItemDecoration(PhotosItemDecoration(4))
        binding.photosRv.adapter = adapter
        adapter.setOnItemClickListener(object : PhotosAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@PhotosActivity, FullPhotoActivity::class.java)
                intent.putExtra(FullPhotoActivityConstants.EXTRA_NAME, viewModel.photos.value?.get(position)?.title)
                intent.putExtra(FullPhotoActivityConstants.EXTRA_IMG_URL, viewModel.photos.value?.get(position)?.url)
                startActivity(intent)
            }
        })
        // Sets the ID which triggers an api request to be made and the photos are retrieved
        if (intent.hasExtra(PhotosActivityConstants.EXTRA_ID)){
            viewModel.setPhotoId(intent.extras!!.getInt(PhotosActivityConstants.EXTRA_ID))
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // On screen orientation change update the grid layout columns
        binding.photosRv.layoutManager = GridLayoutManager(this
            , calculateNoOfColumns(this, PhotosActivityConstants.THUMBNAIL_SIZE) - 1)
    }
}


object PhotosActivityConstants {
    const val EXTRA_ID = "com.example.streamplateandroidtest.EXTRA_ID"
    const val THUMBNAIL_SIZE: Float = 79f // In DPI
}