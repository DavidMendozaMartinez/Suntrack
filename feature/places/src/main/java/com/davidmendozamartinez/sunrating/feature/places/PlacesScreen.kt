package com.davidmendozamartinez.sunrating.feature.places

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.davidmendozamartinez.sunrating.feature.places.model.PlaceTextFieldUiState
import com.davidmendozamartinez.sunrating.feature.places.model.PlaceUiState
import com.davidmendozamartinez.sunrating.feature.places.model.PlacesUiState
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun PlacesScreen(
    uiState: PlacesUiState,
    onPlaceClick: (String) -> Unit,
    onDeletePlaceClick: (String) -> Unit,
    onNameValueChange: (String) -> Unit,
    onLatitudeValueChange: (String) -> Unit,
    onLongitudeValueChange: (String) -> Unit,
    onLocateClick: () -> Unit,
    onCreateClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.exclude(insets = WindowInsets.ime),
    ) { contentPadding ->
        Box(modifier = Modifier.padding(paddingValues = contentPadding)) {
            when (uiState) {
                is PlacesUiState.Loading -> Unit
                is PlacesUiState.Success -> Column {
                    LazyColumn(modifier = Modifier.weight(weight = 1f)) {
                        items(items = uiState.places) {
                            Place(
                                uiState = it,
                                onClick = { onPlaceClick(it.id) },
                                onDeleteClick = { onDeletePlaceClick(it.id) },
                            )
                        }
                    }

                    HorizontalDivider()

                    PlaceTextField(
                        uiState = uiState.placeTextFieldUiState,
                        onNameValueChange = onNameValueChange,
                        onLatitudeValueChange = onLatitudeValueChange,
                        onLongitudeValueChange = onLongitudeValueChange,
                        onLocateClick = onLocateClick,
                        onCreateClick = onCreateClick,
                        modifier = Modifier.imePadding(),
                    )
                }
            }
        }
    }
}

@Composable
private fun Place(
    uiState: PlaceUiState,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(all = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (uiState.isSelected) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }

            Text(text = uiState.name)
        }

        IconButton(onClick = onDeleteClick) {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun PlaceTextField(
    uiState: PlaceTextFieldUiState,
    onNameValueChange: (String) -> Unit,
    onLatitudeValueChange: (String) -> Unit,
    onLongitudeValueChange: (String) -> Unit,
    onLocateClick: () -> Unit,
    onCreateClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        OutlinedTextField(
            value = uiState.name,
            onValueChange = onNameValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Name") },
            maxLines = 1,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = uiState.latitude,
                onValueChange = onLatitudeValueChange,
                modifier = Modifier.weight(weight = 1f),
                label = { Text(text = "Latitude") },
                placeholder = { Text(text = "40.4380986") },
                maxLines = 1,
            )

            OutlinedTextField(
                value = uiState.longitude,
                onValueChange = onLongitudeValueChange,
                modifier = Modifier.weight(weight = 1f),
                label = { Text(text = "Longitude") },
                placeholder = { Text(text = "-3.8443441") },
                maxLines = 1,
            )

            IconButton(
                onClick = onLocateClick,
                modifier = Modifier.border(width = 1.dp, shape = CircleShape, color = LocalContentColor.current)
            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                )
            }
        }
        Button(
            onClick = onCreateClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Create")
        }
    }
}

@Preview
@Composable
private fun PlacesScreenPreview(
    @PreviewParameter(provider = PlacesScreenPreviewParameterProvider::class) uiState: PlacesUiState,
) {
    PlacesScreen(
        uiState = uiState,
        onPlaceClick = {},
        onDeletePlaceClick = {},
        onNameValueChange = {},
        onLatitudeValueChange = {},
        onLongitudeValueChange = {},
        onLocateClick = {},
        onCreateClick = {},
    )
}

private class PlacesScreenPreviewParameterProvider : PreviewParameterProvider<PlacesUiState> {
    override val values: Sequence<PlacesUiState>
        get() = sequenceOf(
            PlacesUiState.Success(
                places = persistentListOf(
                    PlaceUiState(id = "", name = "Place 1", isSelected = false),
                    PlaceUiState(id = "", name = "Place 2", isSelected = true)
                ),
                placeTextFieldUiState = PlaceTextFieldUiState(),
            ),
        )
}
