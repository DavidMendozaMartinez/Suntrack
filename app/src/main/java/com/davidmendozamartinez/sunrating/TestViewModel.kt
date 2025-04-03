package com.davidmendozamartinez.sunrating

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidmendozamartinez.domain.event.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class TestViewModel @Inject constructor(
    eventRepository: EventRepository,
) : ViewModel() {

    init {
        viewModelScope.launch {
            eventRepository.syncUpcomingEvents(
                latitude = "40.4089559",
                longitude = "-3.686312",
            ).onFailure { it.printStackTrace() }
        }
    }
}
