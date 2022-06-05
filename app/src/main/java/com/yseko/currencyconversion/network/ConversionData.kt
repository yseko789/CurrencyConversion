package com.yseko.currencyconversion.network

import com.squareup.moshi.Json

data class ResponseConvert(
    val info: InfoConvert,
    val result: Double
)

data class InfoConvert(
    val rate: Double
)

data class ResponseSymbols(
    val symbols: Map<String, String>
)