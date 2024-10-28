package com.dheeraj.composemvvm.data

import MarsPhotoResponse
import com.example.lib_network.service.RetrofitInstance


class MarsPhotoRepository {
    private val marsPhotoService = RetrofitInstance.marsPhotoService

    suspend fun getMarsPhoto(): MarsPhotoResponse {
        return marsPhotoService.getMarsPhoto()
    }
}
