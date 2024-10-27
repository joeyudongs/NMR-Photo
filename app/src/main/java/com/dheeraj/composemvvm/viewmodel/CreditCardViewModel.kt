package com.dheeraj.composemvvm.viewmodel

import CreditCardResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dheeraj.composemvvm.data.CreditCardRepository
import com.dheeraj.composemvvm.model.Demo
import kotlinx.coroutines.launch

class CreditCardViewModel : ViewModel() {
    private val repository = CreditCardRepository()

    private val _creditCards = MutableLiveData<CreditCardResponse>()
    val creditCards: LiveData<CreditCardResponse> = _creditCards

    fun fetchCreditCards() {
        viewModelScope.launch {
            try {
                val cards = repository.getCreditCards()
                _creditCards.value = cards
                Log.e("FetchCreditCard", _creditCards.value.toString());
            } catch (e: Exception) {
                // Handle error
                Log.e("FetchCreditCard", e.message.toString());
            }
        }
    }

    fun sortPhoto(isAscend:Boolean){
        var currentList: List<Demo>

        if (isAscend) {

            currentList = _creditCards.value?.photos?.sortedBy { it.photoId }!!
        }else{
            currentList = _creditCards.value?.photos?.sortedByDescending { it.photoId }!!

        }

        var cardResponse = CreditCardResponse(currentList)

        _creditCards.value = cardResponse
    }

}
