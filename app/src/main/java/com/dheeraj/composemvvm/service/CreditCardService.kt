package com.dheeraj.composemvvm.service

import CreditCardResponse
import retrofit2.http.GET

interface CreditCardService {
    @GET("photos?sol=1000&page=2&api_key=AyHYvkgDh4gCmDpuFPUj5kmc2nLHsJS5ikGGHKHe")
    suspend fun getCreditCards(): CreditCardResponse
}