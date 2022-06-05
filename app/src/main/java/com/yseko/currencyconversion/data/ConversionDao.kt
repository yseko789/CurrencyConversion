package com.yseko.currencyconversion.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversionDao {
    @Query("SELECT * FROM conversion")
    fun getConversionAll(): Flow<List<Conversion>>

    @Query("SELECT * FROM conversion WHERE id = :id")
    fun getConversion(id: Int): Flow<Conversion>

    @Insert
    suspend fun insert(conversion: Conversion)

    @Query("DELETE FROM conversion WHERE id = :id")
    suspend fun deleteById(id: Int)
}