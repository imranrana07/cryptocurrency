package com.imranrana.paypaycodetest.data.api

import com.imranrana.paypaycodetest.data.model.Currencies
import com.imranrana.paypaycodetest.data.model.CurrencyRates
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {
    //get conversion rated
    @GET("live")
    suspend fun getCurrencyRates(
        @Query("access_key") accessKey: String
    ): Response<CurrencyRates>

    //get supported CurrencyRates
    @GET("list")
    suspend fun getCurrencies(
        @Query("access_key") accessKey: String
    ): Response<Currencies>
}