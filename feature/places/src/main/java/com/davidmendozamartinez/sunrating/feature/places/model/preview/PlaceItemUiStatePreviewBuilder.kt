package com.davidmendozamartinez.sunrating.feature.places.model.preview

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.davidmendozamartinez.sunrating.feature.places.model.PlaceItemUiState

fun buildFakePlaceItemUiState(
    id: String = "",
    name: String = LoremIpsum(words = 2).values.first(),
    isSelected: Boolean = false,
): PlaceItemUiState = PlaceItemUiState(
    id = id,
    name = name,
    isSelected = isSelected,
)
