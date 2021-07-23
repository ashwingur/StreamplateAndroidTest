package com.example.streamplateandroidtest.repositories

import androidx.lifecycle.LiveData
import com.example.streamplateandroidtest.api.RetrofitBuilder
import com.example.streamplateandroidtest.models.Photo
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

object PhotoRepository {

    var job: CompletableJob? = null

    fun getPhotosById(id: Int): LiveData<List<Photo>> {
        job = Job()
        return object: LiveData<List<Photo>>(){
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(IO + it).launch {
                        val photos = RetrofitBuilder.apiService.getPhotos()
                        var filtered_photos = mutableListOf<Photo>()
                        for (photo in photos){
                            if (photo.albumId == id){
                                filtered_photos.add(photo)
                            }
                        }
                        withContext(Dispatchers.Main){
                            value = filtered_photos
                            it.complete()
                        }
                    }
                }
            }
        }
    }
}