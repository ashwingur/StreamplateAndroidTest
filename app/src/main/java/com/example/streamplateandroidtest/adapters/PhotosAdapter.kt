package com.example.streamplateandroidtest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.streamplateandroidtest.R
import com.example.streamplateandroidtest.models.Photo
import com.example.streamplateandroidtest.models.User
import com.squareup.picasso.Picasso

class PhotosAdapter(): RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    var photos: List<Photo> = mutableListOf<Photo>()
    private lateinit var listener : OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.photos_item, parent, false)
        return ViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: PhotosAdapter.ViewHolder, position: Int) {
        // Insert image into imageview with Picasso library
        Picasso.get()
            .load(photos[position].thumbnailUrl)
            .into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    inner class ViewHolder(itemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail_iv)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}