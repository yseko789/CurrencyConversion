package com.yseko.currencyconversion.network

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.apilayer.com/fixer/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FixerService {
    @GET("convert")
    suspend fun getConvert(
        @Query("amount") amount: String,
        @Query("from") convertFrom: String,
        @Query("to") convertTo: String,
        @Query("apikey") apikey: String
    ): ResponseConvert

    @GET("symbols")
    suspend fun getSymbols(
        @Query("apikey") apikey: String
    ): ResponseSymbols

    @GET("latest")
    suspend fun getLatest(
        @Query("base") base:String,
        @Query("symbols")symbols: String,
        @Query("apikey") apikey: String
    ):ResponseDate

    @GET("{date}")
    suspend fun getDate(
        @Path("date") date: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String,
        @Query("apikey") apikey: String
    ): ResponseDate
}

object FixerApi{
    val retrofitService: FixerService by lazy{
        retrofit.create(FixerService::class.java)
    }
}