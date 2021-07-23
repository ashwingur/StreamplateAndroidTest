package com.example.userinfolistactivity.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Photo(
    @Expose
    @SerializedName("albumId")
    val albumId: Int? = null,

    @Expose
    @SerializedName("id")
    val id: Int? = null,

    @Expose
    @SerializedName("title")
    val title: String? = null,

    @Expose
    @SerializedName("url")
    val url: String? = null,

    @Expose
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String? = null,
) {
    override fun toString(): String {
        return "Photo(albumId=${albumId}, id=${id}, name=${title}, email=${url}, phone=${thumbnailUrl})"
    }
}
