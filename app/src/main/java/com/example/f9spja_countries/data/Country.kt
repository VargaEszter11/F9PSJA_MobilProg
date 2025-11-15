package com.example.f9spja_countries.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val capital: String,
    val population: Long,
    val language: String,
    val area: Double,
    val description: String,
    val founded: String,
    val currency: String,
    val visited: Boolean = false,
    val visitDate: String? = null,
    val favorite: Boolean = false
) : Serializable
