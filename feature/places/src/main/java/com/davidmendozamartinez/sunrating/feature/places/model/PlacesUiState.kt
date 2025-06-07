package com.davidmendozamartinez.sunrating.feature.places.model

import com.davidmendozamartinez.sunrating.domain.place.model.Place
import kotlinx.collections.immutable.ImmutableList

sealed interface PlacesUiState {
    data object Loading : PlacesUiState

    data class Success(
        val items: ImmutableList<PlaceItemUiState>,
    ) : PlacesUiState
}

data class PlaceItemUiState(
    val id: String,
    val name: String,
    val isSelected: Boolean,
)

fun Place.toPlaceItemUiState(isSelected: Boolean): PlaceItemUiState = PlaceItemUiState(
    id = id,
    name = name,
    isSelected = isSelected,
)
