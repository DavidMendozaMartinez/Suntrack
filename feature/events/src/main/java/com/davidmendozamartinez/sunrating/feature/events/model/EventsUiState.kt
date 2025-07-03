package com.davidmendozamartinez.sunrating.feature.events.model

sealed interface EventsUiState {
    data object Loading : EventsUiState

    data object NoCurrentPlace : EventsUiState

    data class Success(
        val currentPlaceName: String,
        val eventPagerUiState: EventPagerUiState,
    ) : EventsUiState
}
