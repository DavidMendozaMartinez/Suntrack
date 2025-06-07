package com.davidmendozamartinez.sunrating.feature.places

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidmendozamartinez.sunrating.domain.place.repository.PlaceRepository
import com.davidmendozamartinez.sunrating.feature.places.model.PlaceItemUiState
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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val placeRepository: PlaceRepository,
//    private val locationRepository: LocationRepository,
//    private val eventRepository: EventRepository,
) : ViewModel() {
    private val itemsFlow: Flow<ImmutableList<PlaceItemUiState>> = combine(
        placeRepository.getCurrentPlaceFlow(),
        placeRepository.getPlacesSortedByNameFlow(),
    ) { currentPlace, places ->
        places.map { it.toPlaceItemUiState(isSelected = it.id == currentPlace?.id) }.toImmutableList()
    }

    val uiState: StateFlow<PlacesUiState> = itemsFlow.map {
        PlacesUiState.Success(
            items = it,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = PlacesUiState.Loading
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

//    fun onDeletePlaceClick(placeId: String) {
//        viewModelScope.launch {
//            placeRepository.deletePlace(id = placeId)
//        }
//    }

//    fun onLocateClick() {
//        viewModelScope.launch {
//            val location: Location = locationRepository.getCurrentLocation() ?: return@launch
//        }
//    }

//    fun onCreateClick() {
//        viewModelScope.launch {
//            placeRepository.createPlace(
//                name = ,
//                latitude = ,
//                longitude = ,
//            ).onSuccess { placeId ->
//                eventRepository.syncEvents(placeId = placeId)
//            }
//        }
//    }

    fun onNavigationEventConsumed() {
        _navigation.value = null
    }
}
