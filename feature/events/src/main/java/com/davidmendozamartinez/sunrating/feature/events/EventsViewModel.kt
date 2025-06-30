package com.davidmendozamartinez.sunrating.feature.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidmendozamartinez.sunrating.common.extension.startOfDay
import com.davidmendozamartinez.sunrating.common.extension.today
import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.event.repository.EventRepository
import com.davidmendozamartinez.sunrating.domain.place.model.Place
import com.davidmendozamartinez.sunrating.domain.place.repository.PlaceRepository
import com.davidmendozamartinez.sunrating.feature.events.model.EventPagerUiState
import com.davidmendozamartinez.sunrating.feature.events.model.EventsUiState
import com.davidmendozamartinez.sunrating.feature.events.model.toEventPagerPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.time.Duration.Companion.days
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    placeRepository: PlaceRepository,
) : ViewModel() {
    private val currentPlaceFlow: Flow<Place?> = placeRepository.getCurrentPlaceFlow()

    private val eventsFlow: Flow<List<Event>> = currentPlaceFlow.flatMapLatest {
        if (it == null) return@flatMapLatest flowOf(emptyList())
        eventRepository.getEventsFlow(
            placeId = it.id,
            start = Clock.System.now(),
            end = Clock.System.today().startOfDay() + 3.days,
        )
    }

    val uiState: StateFlow<EventsUiState> = combine(
        currentPlaceFlow,
        eventsFlow,
    ) { currentPlace, events ->
        if (currentPlace == null) {
            EventsUiState.NoCurrentPlace
        } else {
            EventsUiState.Success(
                currentPlaceName = currentPlace.name,
                eventPagerUiState = EventPagerUiState(
                    initialPage = events.indexOfFirst { it.time > Clock.System.now() }.takeIf { it != -1 } ?: events.lastIndex,
                    pages = events.map { it.toEventPagerPageUiState() }.toImmutableList(),
                ),
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = EventsUiState.NoCurrentPlace,
    )

    private val _navigation: MutableStateFlow<EventsNavigation?> = MutableStateFlow(value = null)
    val navigation: StateFlow<EventsNavigation?> = _navigation.asStateFlow()

    init {
        viewModelScope.launch {
            eventRepository.syncEvents()
        }
    }

    fun onCurrentPlaceClick() {
        _navigation.value = EventsNavigation.Places
    }

    fun onSettingsClick() {
        _navigation.value = EventsNavigation.Settings
    }

    fun onNavigationEventConsumed() {
        _navigation.value = null
    }
}
