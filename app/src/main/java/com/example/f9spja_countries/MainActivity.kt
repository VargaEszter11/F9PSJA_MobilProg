package com.example.f9spja_countries;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.f9spja_countries.data.CountryDatabase
import com.example.f9spja_countries.repository.CountryRepository
import com.example.f9spja_countries.ui.screens.CountryListScreen
import com.example.f9spja_countries.viewmodel.CountryViewModel
import com.example.f9spja_countries.ui.theme.F9PSJA_CountriesTheme
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = CountryDatabase.getDatabase(this)
        val repository = CountryRepository(database.countryDao())
        val viewModel = CountryViewModel(repository)

        setContent {
            F9PSJA_CountriesTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CountryListScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountriesAppPreview() {
    F9PSJA_CountriesTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
        }
    }
}
