package com.example.f9spja_countries.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.f9spja_countries.data.Country
import com.example.f9spja_countries.ui.components.AddEditCountryDialog
import com.example.f9spja_countries.ui.components.CountryCard
import com.example.f9spja_countries.ui.theme.*
import com.example.f9spja_countries.viewmodel.CountryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryListScreen(
    viewModel: CountryViewModel,
    modifier: Modifier = Modifier
) {
    val unvisitedCountries by viewModel.unvisitedCountries.collectAsStateWithLifecycle()
    val visitedCountries by viewModel.visitedCountries.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    var showAddDialog by remember { mutableStateOf(false) }
    var countryToEdit by remember { mutableStateOf<Country?>(null) }
    var showVisitedCountries by remember { mutableStateOf(false) }
    var showDeleteConfirmation by remember { mutableStateOf<Country?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBlue)
        ) {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Public,
                            contentDescription = null,
                            tint = TextBlue,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "European Countries",
                            color = TextBlue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { showVisitedCountries = !showVisitedCountries }
                    ) {
                        Icon(
                            imageVector = if (showVisitedCountries) Icons.Default.Home else Icons.Default.Favorite,
                            contentDescription = if (showVisitedCountries) "Show Unvisited" else "Show Visited",
                            tint = TextBlue
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SoftWhite
                )
            )

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = AccentBlue,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }

                showVisitedCountries && visitedCountries.isEmpty() -> {
                    EmptyState(
                        icon = Icons.Default.Favorite,
                        title = "No Visited Countries",
                        subtitle = "Countries you visit will appear here",
                        modifier = Modifier.fillMaxSize()
                    )
                }

                !showVisitedCountries && unvisitedCountries.isEmpty() -> {
                    EmptyState(
                        icon = Icons.Default.Public,
                        title = "No Countries Added",
                        subtitle = "Add your first country to get started!",
                        modifier = Modifier.fillMaxSize()
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                colors = CardDefaults.cardColors(containerColor = AccentBlue)
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = if (showVisitedCountries) Icons.Default.Home else Icons.Default.Public,
                                        contentDescription = null,
                                        tint = SoftWhite,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(
                                        text = if (showVisitedCountries) "Visited Countries" else "Unvisited Countries",
                                        color = SoftWhite,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }

                        val countriesToShow: List<Country> = if (showVisitedCountries) visitedCountries else unvisitedCountries
                        items(countriesToShow) { country ->
                            CountryCard(
                                country = country,
                                onEditClick = { countryToEdit = country },
                                onVisitClick = { countryToVisit ->
                                    if (countryToVisit.visited) {
                                        viewModel.makeCountryUnvisited(countryToVisit.id)
                                    } else {
                                        viewModel.visitCountry(countryToVisit.id)
                                    }
                                },
                                onDeleteClick = { showDeleteConfirmation = country },
                                onFavoriteToggle = { countryToUpdate, favorite ->
                                    val updatedCountry = countryToUpdate.copy(favorite = favorite)
                                    viewModel.updateCountry(updatedCountry)
                                }
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(80.dp))
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { showAddDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = AccentBlue,
            contentColor = SoftWhite
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Country"
            )
        }
    }

    if (showAddDialog || countryToEdit != null) {
        AddEditCountryDialog(
            country = countryToEdit,
            onDismiss = {
                showAddDialog = false
                countryToEdit = null
            },
            onSave = { country ->
                if (countryToEdit != null) {
                    viewModel.updateCountry(country)
                } else {
                    viewModel.addCountry(country)
                }
                showAddDialog = false
                countryToEdit = null
            }
        )
    }

    showDeleteConfirmation?.let { country ->
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = null },
            title = {
                Text(
                    text = "Delete Country",
                    color = TextBlue
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to remove ${country.name} from your list? This action cannot be undone.",
                    color = TextBlue
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteCountry(country)
                        showDeleteConfirmation = null
                    }
                ) {
                    Text(
                        text = "Delete",
                        color = DarkBlue
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDeleteConfirmation = null }
                ) {
                    Text(
                        text = "Cancel",
                        color = TextBlue
                    )
                }
            },
            containerColor = SoftWhite
        )
    }
}

@Composable
private fun EmptyState(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = Blue200
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = TextBlue,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge,
            color = Blue200,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}