package com.example.streamplateandroidtest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// User is a data class since we are only using it to store user data
data class User(

    @Expose
    @SerializedName("id")
    val id: Int? = null,

    @Expose
    @SerializedName("name")
    val name: String? = null,

    @Expose
    @SerializedName("email")
    val email: String? = null,

    @Expose
    @SerializedName("phone")
    val phone: String? = null
){
    override fun toString(): String {
        return "User(id=${id}, name=${name}, email=${email}, phone=${phone})"
    }
}
