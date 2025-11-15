package com.example.f9spja_countries.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.f9spja_countries.data.Country
import com.example.f9spja_countries.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditCountryDialog(
    country: Country?,
    onDismiss: () -> Unit,
    onSave: (Country) -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf(country?.name ?: "") }
    var capital by remember { mutableStateOf(country?.capital ?: "") }
    var population by remember { mutableStateOf(country?.population?.toString() ?: "") }
    var language by remember { mutableStateOf(country?.language ?: "") }
    var area by remember { mutableStateOf(country?.area?.toString() ?: "") }
    var description by remember { mutableStateOf(country?.description ?: "") }
    var founded by remember { mutableStateOf(country?.founded ?: "") }
    var currency by remember { mutableStateOf(country?.currency ?: "") }

    var nameError by remember { mutableStateOf("") }
    var capitalError by remember { mutableStateOf("") }
    var populationError by remember { mutableStateOf("") }
    var currencyError by remember { mutableStateOf("") }
    var showErrors by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (country == null) "Add New Country" else "Edit Country",
                color = TextBlue,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        if (showErrors && nameError.isNotEmpty()) {
                            nameError = if (it.isBlank()) "Country name is required" else ""
                        }
                    },
                    label = { Text("Country Name*", color = TextBlue) },
                    modifier = Modifier.fillMaxWidth(),
                    isError = nameError.isNotEmpty(),
                    supportingText = if (nameError.isNotEmpty()) {
                        { Text(nameError, color = MaterialTheme.colorScheme.error) }
                    } else null,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AccentBlue,
                        unfocusedBorderColor = Blue200,
                        focusedLabelColor = AccentBlue,
                        unfocusedLabelColor = TextBlue,
                        errorBorderColor = MaterialTheme.colorScheme.error,
                        errorLabelColor = MaterialTheme.colorScheme.error
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Public,
                            contentDescription = null,
                            tint = AccentBlue
                        )
                    }
                )

                OutlinedTextField(
                    value = capital,
                    onValueChange = {
                        capital = it
                        if (showErrors && capitalError.isNotEmpty()) {
                            capitalError = if (it.isBlank()) "Capital is required" else ""
                        }
                    },
                    label = { Text("Capital*", color = TextBlue) },
                    modifier = Modifier.fillMaxWidth(),
                    isError = capitalError.isNotEmpty(),
                    supportingText = if (capitalError.isNotEmpty()) {
                        { Text(capitalError, color = MaterialTheme.colorScheme.error) }
                    } else null,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AccentBlue,
                        unfocusedBorderColor = Blue200,
                        focusedLabelColor = AccentBlue,
                        unfocusedLabelColor = TextBlue,
                        errorBorderColor = MaterialTheme.colorScheme.error,
                        errorLabelColor = MaterialTheme.colorScheme.error
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.LocationCity,
                            contentDescription = null,
                            tint = AccentBlue
                        )
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = population,
                        onValueChange = {
                            population = it
                            if (showErrors && populationError.isNotEmpty()) {
                                populationError = if (it.isBlank()) "Population is required" else ""
                            }
                        },
                        label = { Text("Population*", color = TextBlue) },
                        modifier = Modifier.weight(1f),
                        isError = populationError.isNotEmpty(),
                        supportingText = if (populationError.isNotEmpty()) {
                            { Text(populationError, color = MaterialTheme.colorScheme.error) }
                        } else null,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = AccentBlue,
                            unfocusedBorderColor = Blue200,
                            focusedLabelColor = AccentBlue,
                            unfocusedLabelColor = TextBlue,
                            errorBorderColor = MaterialTheme.colorScheme.error,
                            errorLabelColor = MaterialTheme.colorScheme.error
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Group,
                                contentDescription = null,
                                tint = AccentBlue
                            )
                        }
                    )

                    OutlinedTextField(
                        value = area,
                        onValueChange = { area = it },
                        label = { Text("Area (km²)", color = TextBlue) },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = AccentBlue,
                            unfocusedBorderColor = Blue200,
                            focusedLabelColor = AccentBlue,
                            unfocusedLabelColor = TextBlue
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Map,
                                contentDescription = null,
                                tint = AccentBlue
                            )
                        }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = language,
                        onValueChange = { language = it },
                        label = { Text("Language", color = TextBlue) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = AccentBlue,
                            unfocusedBorderColor = Blue200,
                            focusedLabelColor = AccentBlue,
                            unfocusedLabelColor = TextBlue
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = null,
                                tint = AccentBlue
                            )
                        }
                    )

                    OutlinedTextField(
                        value = currency,
                        onValueChange = {
                            currency = it
                            if (showErrors && currencyError.isNotEmpty()) {
                                currencyError = if (it.isBlank()) "Currency is required" else ""
                            }
                        },
                        label = { Text("Currency*", color = TextBlue) },
                        modifier = Modifier.weight(1f),
                        isError = currencyError.isNotEmpty(),
                        supportingText = if (currencyError.isNotEmpty()) {
                            { Text(currencyError, color = MaterialTheme.colorScheme.error) }
                        } else null,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = AccentBlue,
                            unfocusedBorderColor = Blue200,
                            focusedLabelColor = AccentBlue,
                            unfocusedLabelColor = TextBlue,
                            errorBorderColor = MaterialTheme.colorScheme.error,
                            errorLabelColor = MaterialTheme.colorScheme.error
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.AttachMoney,
                                contentDescription = null,
                                tint = AccentBlue
                            )
                        }
                    )
                }

                OutlinedTextField(
                    value = founded,
                    onValueChange = { founded = it },
                    label = { Text("Founded (YYYY-MM-DD)", color = TextBlue) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AccentBlue,
                        unfocusedBorderColor = Blue200,
                        focusedLabelColor = AccentBlue,
                        unfocusedLabelColor = TextBlue
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            tint = AccentBlue
                        )
                    }
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description", color = TextBlue) },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2,
                    maxLines = 3,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AccentBlue,
                        unfocusedBorderColor = Blue200,
                        focusedLabelColor = AccentBlue,
                        unfocusedLabelColor = TextBlue
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = null,
                            tint = AccentBlue
                        )
                    }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    nameError = if (name.isBlank()) "Country name is required" else ""
                    capitalError = if (capital.isBlank()) "Capital is required" else ""
                    populationError = if (population.isBlank()) "Population is required" else ""
                    currencyError = if (currency.isBlank()) "Currency is required" else ""

                    showErrors = true

                    if (nameError.isEmpty() && capitalError.isEmpty() &&
                        populationError.isEmpty() && currencyError.isEmpty()) {
                        val newCountry = Country(
                            id = country?.id ?: 0,
                            name = name.trim(),
                            capital = capital.trim(),
                            population = population.toLongOrNull() ?: 0,
                            language = language.trim(),
                            area = area.toDoubleOrNull() ?: 0.0,
                            description = description.trim(),
                            founded = founded.trim(),
                            currency = currency.trim(),
                            visited = country?.visited ?: false,
                            visitDate = country?.visitDate,
                            favorite = country?.favorite ?: false
                        )
                        onSave(newCountry)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = AccentBlue,
                    contentColor = SoftWhite
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = if (country == null) "Add Country" else "Update Country",
                    fontWeight = FontWeight.Medium
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = TextBlue
                )
            ) {
                Text("Cancel")
            }
        },
        containerColor = SoftWhite,
        shape = RoundedCornerShape(16.dp)
    )
}