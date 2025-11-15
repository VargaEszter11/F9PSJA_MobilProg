package com.example.f9spja_countries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.f9spja_countries.data.Country
import com.example.f9spja_countries.repository.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryViewModel(private val repository: CountryRepository) : ViewModel() {

    private val _unvisitedCountries = MutableStateFlow<List<Country>>(emptyList())
    val unvisitedCountries: StateFlow<List<Country>> = _unvisitedCountries.asStateFlow()

    private val _visitedCountries = MutableStateFlow<List<Country>>(emptyList())
    val visitedCountries: StateFlow<List<Country>> = _visitedCountries.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadCountries()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getAllUnvisitedCountries().collect { countries ->
                _unvisitedCountries.value = countries
                _isLoading.value = false
            }
        }

        viewModelScope.launch {
            repository.getAllVisitedCountries().collect { countries ->
                _visitedCountries.value = countries
            }
        }
    }

    fun addCountry(country: Country) {
        viewModelScope.launch {
            repository.insertCountry(country)
        }
    }

    fun updateCountry(country: Country) {
        viewModelScope.launch {
            repository.updateCountry(country)
        }
    }

    fun deleteCountry(country: Country) {
        viewModelScope.launch {
            repository.deleteCountry(country)
        }
    }

    fun visitCountry(country: Country) {
        viewModelScope.launch {
            repository.visitCountry(country)
        }
    }

    fun makeCountryUnvisited(country: Country) {
        viewModelScope.launch {
            repository.makeCountryUnvisited(country)
        }
    }
}