package com.davidmendozamartinez.sunrating.feature.places

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidmendozamartinez.sunrating.domain.location.repository.Location
import com.davidmendozamartinez.sunrating.domain.location.repository.LocationRepository
import com.davidmendozamartinez.sunrating.domain.place.repository.PlaceRepository
import com.davidmendozamartinez.sunrating.feature.places.model.PlaceTextFieldUiState
import com.davidmendozamartinez.sunrating.feature.places.model.PlaceUiState
import com.davidmendozamartinez.sunrating.feature.places.model.PlacesUiState
import com.davidmendozamartinez.sunrating.feature.places.model.toPlaceUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val placeRepository: PlaceRepository,
    private val locationRepository: LocationRepository,
) : ViewModel() {
    private val placesFlow: Flow<ImmutableList<PlaceUiState>> = combine(
        placeRepository.getCurrentPlaceFlow(),
        placeRepository.getPlacesSortedByNameFlow(),
    ) { currentPlace, places ->
        places.map { it.toPlaceUiState(isSelected = it.id == currentPlace?.id) }.toImmutableList()
    }

    private val placeTextFieldUiState: MutableStateFlow<PlaceTextFieldUiState> = MutableStateFlow(value = PlaceTextFieldUiState())

    val uiState: StateFlow<PlacesUiState> = combine(
        placesFlow,
        placeTextFieldUiState,
    ) { places, placeTextFieldUiState ->
        PlacesUiState.Success(
            places = places,
            placeTextFieldUiState = placeTextFieldUiState,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = PlacesUiState.Loading
    )

    fun onPlaceClick(placeId: String) {
        viewModelScope.launch {
            placeRepository.setCurrentPlace(id = placeId)
        }
    }

    fun onDeletePlaceClick(placeId: String) {
        viewModelScope.launch {
            placeRepository.deletePlace(id = placeId)
        }
    }

    fun onNameValueChange(value: String) {
        placeTextFieldUiState.update { it.copy(name = value) }
    }

    fun onLatitudeValueChange(value: String) {
        placeTextFieldUiState.update { it.copy(latitude = value) }
    }

    fun onLongitudeValueChange(value: String) {
        placeTextFieldUiState.update { it.copy(longitude = value) }
    }

    fun onLocateClick() {
        viewModelScope.launch {
            val location: Location = locationRepository.getCurrentLocation() ?: return@launch
            placeTextFieldUiState.update { it.copy(latitude = location.first.toString(), longitude = location.second.toString()) }
        }
    }

    fun onCreateClick() {
        viewModelScope.launch {
            val textFieldUiState: PlaceTextFieldUiState = placeTextFieldUiState.value
            placeRepository.createPlace(
                name = textFieldUiState.name,
                latitude = textFieldUiState.latitude.toDoubleOrNull() ?: return@launch,
                longitude = textFieldUiState.longitude.toDoubleOrNull() ?: return@launch,
            )

            placeTextFieldUiState.update { it.copy(name = "", latitude = "", longitude = "") }
        }
    }
}
