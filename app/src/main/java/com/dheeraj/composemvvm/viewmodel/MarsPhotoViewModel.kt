package com.dheeraj.composemvvm.viewmodel

import MarsPhotoResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dheeraj.composemvvm.data.MarsPhotoRepository
import com.dheeraj.composemvvm.model.Demo
import kotlinx.coroutines.launch

class MarsPhotoViewModel : ViewModel() {
    private val repository = MarsPhotoRepository()

    private val _marsPhoto = MutableLiveData<MarsPhotoResponse>()
    val marsPhotoResponseLiveData: LiveData<MarsPhotoResponse> = _marsPhoto


    // LiveData to handle error messages
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun fetchMarsPhoto() {
        viewModelScope.launch {
            try {
                val cards = repository.getMarsPhoto()
                _marsPhoto.value = cards
                _errorMessage.value = null // Clear any previous error
            } catch (e: Exception) {
                // Handle error
                _errorMessage.value = "Connection failed: ${e.message}"
                Log.e("fetchMarsPhoto",e.message.toString())
            }
        }
    }

    fun sortPhoto(isAscend:Boolean){
        var currentList: List<Demo>

        if (isAscend) {

            currentList = _marsPhoto.value?.photos?.sortedBy { it.photoId }!!
        }else{
            currentList = _marsPhoto.value?.photos?.sortedByDescending { it.photoId }!!

        }

        var cardResponse = MarsPhotoResponse(currentList)

        _marsPhoto.value = cardResponse
    }

}
