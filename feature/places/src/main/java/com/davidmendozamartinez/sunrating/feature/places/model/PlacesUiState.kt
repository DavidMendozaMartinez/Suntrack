package com.davidmendozamartinez.sunrating.feature.places.model

import com.davidmendozamartinez.sunrating.domain.place.model.Place
import kotlinx.collections.immutable.ImmutableList

data class PlacesUiState(
    val bottomBarUiState: PlacesBottomBarUiState,
    val contentUiState: PlacesContentUiState,
)

sealed interface PlacesContentUiState {
    data object Loading : PlacesContentUiState

    data class Success(
        val items: ImmutableList<PlaceItemUiState>,
    ) : PlacesContentUiState
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
