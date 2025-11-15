package com.example.f9spja_countries.repository

import com.example.f9spja_countries.data.Country
import com.example.f9spja_countries.data.CountryDao
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.*

class CountryRepository(private val countryDao: CountryDao) {

    fun getAllUnvisitedCountries(): Flow<List<Country>> = countryDao.getAllUnvisitedCountries()

    fun getAllVisitedCountries(): Flow<List<Country>> = countryDao.getAllVisitedCountries()

    fun getAllCountries(): Flow<List<Country>> = countryDao.getAllCountries()

    suspend fun getCountryById(countryId: Long): Country? = countryDao.getCountryById(countryId)

    suspend fun insertCountry(country: Country): Long = countryDao.insertCountry(country)

    suspend fun updateCountry(country: Country) = countryDao.updateCountry(country)

    suspend fun deleteCountry(country: Country) = countryDao.deleteCountry(country)

    suspend fun visitCountry(country: Country) {
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        countryDao.markAsVisited(country, currentDate)
    }

    suspend fun makeCountryUnvisited(country: Country) = countryDao.markAsUnvisited(country)
}