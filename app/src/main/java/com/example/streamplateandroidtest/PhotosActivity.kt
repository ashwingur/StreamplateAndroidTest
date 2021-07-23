package com.example.streamplateandroidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streamplateandroidtest.adapters.PhotosAdapter
import com.example.streamplateandroidtest.adapters.UserListAdapter
import com.example.streamplateandroidtest.databinding.ActivityPhotosBinding
import com.example.streamplateandroidtest.viewmodels.PhotosViewModel
import com.example.streamplateandroidtest.viewmodels.UserListViewModel

class PhotosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotosBinding
    lateinit var viewModel: PhotosViewModel
    private val adapter: PhotosAdapter = PhotosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initRecyclerView()

    }

    fun initViewModel(){
        viewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
        viewModel.photos.observe(this, Observer {
            adapter.photos = it
            adapter.notifyDataSetChanged()
        })
    }

    fun initRecyclerView(){
        binding.photosRv.layoutManager = GridLayoutManager(this,2)
        binding.photosRv.adapter = adapter
        adapter.setOnItemClickListener(object : PhotosAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@PhotosActivity, "$position", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this@UserListActivity, PhotosActivity::class.java)
//                intent.putExtra(PhotosActivityConstants.EXTRA_ID, viewModel.users.value?.get(position)?.id)
//                startActivity(intent)
            }
        })
        viewModel.setPhotoId(intent.extras!!.getInt(PhotosActivityConstants.EXTRA_ID))
    }
}

object PhotosActivityConstants {
    const val EXTRA_ID = "com.example.streamplateandroidtest.EXTRA_ID"
}