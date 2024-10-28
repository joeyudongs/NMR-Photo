package com.dheeraj.composemvvm.data

import MarsPhotoResponse
import com.dheeraj.composemvvm.service.RetrofitInstance


class MarsPhotoRepository {
    private val marsPhotoService = RetrofitInstance.marsPhotoService

    suspend fun getMarsPhoto(): MarsPhotoResponse {
        return marsPhotoService.getMarsPhoto()
    }
}
