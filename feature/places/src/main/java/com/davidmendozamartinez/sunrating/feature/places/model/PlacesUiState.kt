package com.davidmendozamartinez.sunrating.feature.places.model

import com.davidmendozamartinez.sunrating.domain.place.model.Place
import kotlinx.collections.immutable.ImmutableList

sealed interface PlacesUiState {
    data object Loading : PlacesUiState

    data class Success(
        val places: ImmutableList<PlaceUiState>,
        val placeTextFieldUiState: PlaceTextFieldUiState,
    ) : PlacesUiState
}

data class PlaceUiState(
    val id: String,
    val name: String,
    val isSelected: Boolean,
)

data class PlaceTextFieldUiState(
    val name: String = "",
    val latitude: String = "",
    val longitude: String = "",
)

fun Place.toPlaceUiState(isSelected: Boolean): PlaceUiState = PlaceUiState(
    id = id,
    name = name,
    isSelected = isSelected,
)
