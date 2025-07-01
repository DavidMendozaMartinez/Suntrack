package com.davidmendozamartinez.sunrating.feature.places

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidmendozamartinez.sunrating.domain.event.repository.EventRepository
import com.davidmendozamartinez.sunrating.domain.location.repository.Location
import com.davidmendozamartinez.sunrating.domain.location.repository.LocationRepository
import com.davidmendozamartinez.sunrating.domain.place.repository.PlaceRepository
import com.davidmendozamartinez.sunrating.feature.places.model.PlaceItemOptionUiState
import com.davidmendozamartinez.sunrating.feature.places.model.PlaceItemUiState
import com.davidmendozamartinez.sunrating.feature.places.model.PlaceNameTextFieldUiState
import com.davidmendozamartinez.sunrating.feature.places.model.PlacesBottomBarUiState
import com.davidmendozamartinez.sunrating.feature.places.model.PlacesContentUiState
import com.davidmendozamartinez.sunrating.feature.places.model.PlacesUiState
import com.davidmendozamartinez.sunrating.feature.places.model.toPlaceItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val placeRepository: PlaceRepository,
    private val locationRepository: LocationRepository,
    private val eventRepository: EventRepository,
) : ViewModel() {
    private val itemsFlow: Flow<ImmutableList<PlaceItemUiState>> = combine(
        placeRepository.getCurrentPlaceFlow(),
        placeRepository.getPlacesSortedByNameFlow(),
    ) { currentPlace, places ->
        places.map { it.toPlaceItemUiState(isSelected = it.id == currentPlace?.id) }.toImmutableList()
    }

    private val bottomBarUiState: MutableStateFlow<PlacesBottomBarUiState> = MutableStateFlow(value = PlacesBottomBarUiState())

    val uiState: StateFlow<PlacesUiState> = combine(
        itemsFlow,
        bottomBarUiState,
    ) { items, bottomBarUiState ->
        PlacesUiState(
            bottomBarUiState = bottomBarUiState,
            contentUiState = if (items.isNotEmpty()) PlacesContentUiState.Success(items = items) else PlacesContentUiState.Empty,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = PlacesUiState(
            bottomBarUiState = bottomBarUiState.value,
            contentUiState = PlacesContentUiState.Loading,
        )
    )

    private val _navigation: MutableStateFlow<PlacesNavigation?> = MutableStateFlow(value = null)
    val navigation: StateFlow<PlacesNavigation?> = _navigation.asStateFlow()

    fun onBackClick() {
        _navigation.value = PlacesNavigation.Back
    }

    fun onPlaceClick(placeId: String) {
        viewModelScope.launch {
            placeRepository.setCurrentPlace(id = placeId)
            _navigation.value = PlacesNavigation.Back
        }
    }

    fun onPlaceOptionClick(
        placeId: String,
        optionUiState: PlaceItemOptionUiState,
    ) {
        when (optionUiState) {
            PlaceItemOptionUiState.DELETE -> viewModelScope.launch { placeRepository.deletePlace(id = placeId) }
        }
    }

    fun onPlaceNameValueChange(value: String) {
        bottomBarUiState.update {
            it.copy(
                placeNameTextFieldUiState = PlaceNameTextFieldUiState(value = value),
                isCreateButtonEnabled = value.isNotBlank(),
            )
        }
    }

    fun onCreatePlaceClick() {
        viewModelScope.launch { createPlace() }
    }

    fun onNavigationEventConsumed() {
        _navigation.value = null
    }

    private suspend fun createPlace() {
        val location: Location = locationRepository.getCurrentLocation() ?: return

        placeRepository.createPlace(
            name = bottomBarUiState.value.placeNameTextFieldUiState.value.trim(),
            latitude = location.first,
            longitude = location.second,
        ).onSuccess { placeId ->
            bottomBarUiState.value = PlacesBottomBarUiState()
            eventRepository.syncEvents(placeId = placeId)
        }
    }
}
