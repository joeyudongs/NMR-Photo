package com.dheeraj.composemvvm.data

import CreditCardResponse
import com.dheeraj.composemvvm.service.RetrofitInstance


class CreditCardRepository {
    private val creditCardService = RetrofitInstance.creditCardService

    suspend fun getCreditCards(): CreditCardResponse {
        return creditCardService.getCreditCards()
    }
}
