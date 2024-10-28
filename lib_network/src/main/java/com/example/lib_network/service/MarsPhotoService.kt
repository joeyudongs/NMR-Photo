package com.example.lib_network.service

import MarsPhotoResponse
import retrofit2.http.GET

interface MarsPhotoService {
    @GET("photos?sol=1000&page=2&api_key=AyHYvkgDh4gCmDpuFPUj5kmc2nLHsJS5ikGGHKHe")
    suspend fun getMarsPhoto(): MarsPhotoResponse
}