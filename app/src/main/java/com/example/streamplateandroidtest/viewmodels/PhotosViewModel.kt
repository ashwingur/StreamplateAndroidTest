package com.example.streamplateandroidtest.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.streamplateandroidtest.models.Photo
import com.example.streamplateandroidtest.repositories.PhotoRepository

class PhotosViewModel(application: Application) : AndroidViewModel(application) {

    private val _id: MutableLiveData<Int> = MutableLiveData()

    // The switch map is observing _id, and when it is changed the livedata photos object updates as well
    val photos: LiveData<List<Photo>> = Transformations
        .switchMap(_id){ id ->
            PhotoRepository.getPhotosById(id, application)
        }

    fun setPhotoId(id: Int){
        // If the id is the same nothing happens, otherwise update it which causes the photo livedata
        // to update as well
        if (_id.value == id){
            return
        }
        _id.value = id
    }
}