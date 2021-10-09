package com.imranrana.paypaycodetest.data.repository

import com.imranrana.paypaycodetest.data.model.Currencies
import com.imranrana.paypaycodetest.data.model.CurrencyRates

interface CurrencyRepoModel {
    suspend fun getCurrencyRates(accessToken: String): CurrencyRates
    suspend fun getCurrencies(accessToken: String): Currencies
}