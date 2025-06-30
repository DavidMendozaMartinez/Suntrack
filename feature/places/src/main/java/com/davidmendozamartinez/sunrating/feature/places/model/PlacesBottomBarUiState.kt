package com.davidmendozamartinez.sunrating.feature.places.model

data class PlacesBottomBarUiState(
    val placeNameTextFieldUiState: PlaceNameTextFieldUiState = PlaceNameTextFieldUiState(),
    val isCreateButtonEnabled: Boolean = false,
)

data class PlaceNameTextFieldUiState(
    val value: String = "",
    val isEnabled: Boolean = true,
)
