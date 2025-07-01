package com.davidmendozamartinez.sunrating.feature.places.model

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
