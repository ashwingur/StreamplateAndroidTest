package com.example.streamplateandroidtest.repositories

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.streamplateandroidtest.api.RetrofitBuilder
import com.example.streamplateandroidtest.models.Photo
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

object PhotoRepository {

    var job: CompletableJob? = null

    // Similar code as the user repository, but also filters the photos that have a certain albumID
    fun getPhotosById(id: Int, context: Context): LiveData<List<Photo>> {
        job = Job()
        return object: LiveData<List<Photo>>(){
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(IO + it).launch {
                        val response = RetrofitBuilder.apiService.getPhotos()
                        if (response.code() == 200) {
                            val filteredPhotos = mutableListOf<Photo>()
                            val result: List<Photo>? = response.body()
                            for (photo in result!!) {
                                if (photo.albumId == id) {
                                    filteredPhotos.add(photo)
                                }
                            }
                            withContext(Dispatchers.Main) {
                                value = filteredPhotos
                            }
                        } else {
                            withContext(Dispatchers.Main){
                                Toast.makeText(context, "Error in retrieving photos [Status: ${response.code()}]", Toast.LENGTH_LONG).show()
                            }
                            it.complete()
                        }
                    }
                }
            }
        }
    }
}