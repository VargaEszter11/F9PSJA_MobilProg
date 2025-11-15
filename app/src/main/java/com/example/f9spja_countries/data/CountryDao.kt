package com.example.f9spja_countries.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Query("SELECT * FROM countries WHERE visited = 0 ORDER BY name ASC")
    fun getAllUnvisitedCountries(): Flow<List<Country>>

    @Query("SELECT * FROM countries WHERE visited = 1 ORDER BY visitDate DESC")
    fun getAllVisitedCountries(): Flow<List<Country>>

    @Query("SELECT * FROM countries ORDER BY name ASC")
    fun getAllCountries(): Flow<List<Country>>

    @Query("SELECT * FROM countries WHERE id = :countryId")
    suspend fun getCountryById(countryId: Long): Country?

    @Insert
    suspend fun insertCountry(country: Country): Long

    @Update
    suspend fun updateCountry(country: Country)

    @Delete
    suspend fun deleteCountry(country: Country)

    @Query("UPDATE countries SET visited = 1, visitDate = :visitDate WHERE id = :countryId")
    suspend fun markAsVisited(countryId: Long, visitDate: String)

    @Query("UPDATE countries SET visited = 0, visitDate = NULL WHERE id = :countryId")
    suspend fun markAsUnvisited(countryId: Long)
}