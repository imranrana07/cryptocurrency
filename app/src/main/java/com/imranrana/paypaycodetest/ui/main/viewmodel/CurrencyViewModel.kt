package com.imranrana.paypaycodetest.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imranrana.paypaycodetest.data.model.Currencies
import com.imranrana.paypaycodetest.data.model.CurrencyRates
import com.imranrana.paypaycodetest.data.repository.CurrencyRepository
import com.imranrana.paypaycodetest.utils.ApiException
import com.imranrana.paypaycodetest.utils.GONE
import com.imranrana.paypaycodetest.utils.VISIBLE
import kotlinx.coroutines.launch
import java.util.ArrayList

class CurrencyViewModel(private val currencyRepository: CurrencyRepository): ViewModel() {
    val currencyRates = MutableLiveData<CurrencyRates>()
    val currencies = MutableLiveData<Currencies>()
    val error = MutableLiveData<String>()
    val progressBar = MutableLiveData<Int>()

    fun getCurrenciesRate(accessToken: String) {
        try {
            progressBar.postValue(VISIBLE)
            viewModelScope.launch {
                val response = currencyRepository.getCurrencyRates(accessToken)
                currencyRates.postValue(response)
                progressBar.postValue(GONE)
            }
        }catch (e: ApiException){
            progressBar.postValue(GONE)
            error.postValue(e.message)
        }
    }

    fun getCurrencies(accessToken: String) {
        try {
            progressBar.postValue(VISIBLE)
            viewModelScope.launch {
                val response = currencyRepository.getCurrencies(accessToken)
                currencies.postValue(response)
                progressBar.postValue(GONE)
            }
        }catch (e: ApiException){
            progressBar.postValue(GONE)
            error.postValue(e.message)
        }
    }

}