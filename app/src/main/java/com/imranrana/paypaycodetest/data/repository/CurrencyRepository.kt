package com.imranrana.paypaycodetest.data.repository

import com.imranrana.paypaycodetest.data.api.ApiResponse
import com.imranrana.paypaycodetest.data.model.Currencies
import com.imranrana.paypaycodetest.data.model.CurrencyRates
import com.imranrana.paypaycodetest.utils.apiCall

class CurrencyRepository: CurrencyRepoModel{
    override suspend fun getCurrencyRates(accessToken: String): CurrencyRates {
        return ApiResponse.getResult { apiCall?.getCurrencyRates(accessToken)!! }
    }

    override suspend fun getCurrencies(accessToken: String): Currencies {
        return ApiResponse.getResult { apiCall?.getCurrencies(accessToken)!! }
    }

}