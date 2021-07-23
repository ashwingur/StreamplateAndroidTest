package com.example.streamplateandroidtest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.streamplateandroidtest.R
import com.example.streamplateandroidtest.models.User

class UserListAdapter(): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    lateinit var users: List<User>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.userlist_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserListAdapter.ViewHolder, position: Int) {
        holder.name.text = users[position].name
        holder.phone.text = users[position].phone
        holder.email.text = users[position].email
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.user_name_tv)
        val email: TextView = itemView.findViewById(R.id.user_email_tv)
        val phone: TextView = itemView.findViewById(R.id.user_phone_tv)
    }
}