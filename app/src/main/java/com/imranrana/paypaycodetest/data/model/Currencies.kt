package com.imranrana.paypaycodetest.data.model

data class Currencies(
    val privacy: String,
    val currencies: Map<String,String>,
    val success: Boolean,
    val terms: String
)