package com.imranrana.paypaycodetest.data.model

import java.io.Serializable

data class CurrencyRates(
    val privacy: String,
    val quotes: Map<String,Double>,
    val source: String,
    val success: Boolean,
    val terms: String,
    val timestamp: Int
):Serializable