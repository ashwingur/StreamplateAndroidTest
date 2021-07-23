package com.example.streamplateandroidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streamplateandroidtest.adapters.UserListAdapter
import com.example.streamplateandroidtest.databinding.ActivityUserlistBinding
import com.example.streamplateandroidtest.viewmodels.UserListViewModel

class UserListActivity : AppCompatActivity() {

    lateinit var viewModel: UserListViewModel
    private lateinit var binding: ActivityUserlistBinding
    private val adapter: UserListAdapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "User Details"

        initViewModel()
        initRecyclerView()
    }

    fun initViewModel(){
        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        viewModel.users.observe(this, Observer {
            adapter.users = it
            adapter.notifyDataSetChanged()
        })
    }

    fun initRecyclerView(){
        binding.userlistRv.layoutManager = LinearLayoutManager(this)
        binding.userlistRv.adapter = adapter
        adapter.setOnItemClickListener(object : UserListAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@UserListActivity, PhotosActivity::class.java)
                intent.putExtra(PhotosActivityConstants.EXTRA_ID, viewModel.users.value?.get(position)?.id)
                startActivity(intent)
            }
        })
    }

}