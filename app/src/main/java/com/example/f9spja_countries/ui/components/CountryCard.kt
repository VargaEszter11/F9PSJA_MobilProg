package com.example.f9spja_countries.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.f9spja_countries.data.Country
import com.example.f9spja_countries.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCard(
    country: Country,
    onEditClick: (Country) -> Unit,
    onVisitClick: (Country) -> Unit,
    onDeleteClick: (Country) -> Unit,
    onFavoriteToggle: (Country, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = SoftWhite
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = country.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = TextBlue,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (country.visited) {
                    Surface(
                        color = AccentBlue,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = SoftWhite,
                                modifier = Modifier.size(12.dp)
                            )
                            Text(
                                text = "Visited",
                                color = SoftWhite,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    CountryDetailRow(
                        icon = Icons.Default.LocationCity,
                        text = country.capital,
                        color = Blue200
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CountryDetailRow(
                        icon = Icons.Default.Public,
                        text = "${country.population / 1_000_000}M people",
                        color = Blue200
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    CountryDetailRow(
                        icon = Icons.Default.Language,
                        text = country.language,
                        color = Blue200
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CountryDetailRow(
                        icon = Icons.Default.AttachMoney,
                        text = country.currency,
                        color = Blue200
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    CountryDetailRow(
                        icon = Icons.Default.Map,
                        text = "${String.format("%.0f", country.area)} km²",
                        color = Blue200
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }

            if (country.description.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = country.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextBlue,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = if (country.favorite) AccentBlue else LightBlue,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable { onFavoriteToggle(country, !country.favorite) },
                    contentAlignment = Alignment.Center
                ) {
                    if (country.favorite) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = SoftWhite,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Text(
                    text = "Favorite",
                    color = TextBlue,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (country.visited) {
                    OutlinedButton(
                        onClick = { onVisitClick(country) },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = AccentBlue
                        ),
                        border = BorderStroke(1.dp, AccentBlue),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.height(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Unvisited",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Unvisited")
                    }
                } else {
                    Button(
                        onClick = { onVisitClick(country) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AccentBlue,
                            contentColor = SoftWhite
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.height(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Visit",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Visited")
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { onEditClick(country) },
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = LightBlue,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = TextBlue,
                            containerColor = Color.Transparent
                        ),
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit",
                            modifier = Modifier.size(20.dp),
                            tint = TextBlue
                        )
                    }

                    IconButton(
                        onClick = { onDeleteClick(country) },
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = LightBlue,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = DarkBlue,
                            containerColor = Color.Transparent
                        ),
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            modifier = Modifier.size(20.dp),
                            tint = DarkBlue
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CountryDetailRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    color: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = color
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = TextBlue,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}