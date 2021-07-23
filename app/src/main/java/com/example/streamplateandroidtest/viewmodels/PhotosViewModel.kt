package com.example.streamplateandroidtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.streamplateandroidtest.models.Photo
import com.example.streamplateandroidtest.repositories.PhotoRepository

class PhotosViewModel : ViewModel() {

    private val _id: MutableLiveData<Int> = MutableLiveData()

    // The switch map is observing _id, and when it is changed the livedata photos object updates as well
    val photos: LiveData<List<Photo>> = Transformations
        .switchMap(_id){ id ->
            PhotoRepository.getPhotosById(id)
        }

    fun setPhotoId(id: Int){
        if (_id.value == id){
            return
        }
        _id.value = id
    }
}