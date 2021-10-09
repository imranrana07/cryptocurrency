package com.imranrana.paypaycodetest.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imranrana.paypaycodetest.data.repository.CurrencyRepository

@Suppress("UNCHECKED_CAST")
class CurrencyViewModelFactory(private val currencyRepository: CurrencyRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrencyViewModel(currencyRepository) as T
    }
}