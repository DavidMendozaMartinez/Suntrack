package com.davidmendozamartinez.sunrating.feature.events

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.davidmendozamartinez.sunrating.feature.events.model.EventsUiState
import kotlinx.serialization.Serializable

@Serializable
object EventsRoute

sealed interface EventsNavigation {
    data object Places : EventsNavigation

    data object Settings : EventsNavigation
}

fun NavGraphBuilder.eventsScreen(onNavigationEvent: (EventsNavigation) -> Unit) {
    composable<EventsRoute> {
        EventsRoute(onNavigationEvent = onNavigationEvent)
    }
}

@Composable
internal fun EventsRoute(
    onNavigationEvent: (EventsNavigation) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EventsViewModel = hiltViewModel(),
) {
    val uiState: EventsUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navigation: EventsNavigation? by viewModel.navigation.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = navigation) {
        navigation?.let { onNavigationEvent(it) }
        viewModel.onNavigationEventConsumed()
    }

    EventsScreen(
        uiState = uiState,
        onCurrentPlaceClick = viewModel::onCurrentPlaceClick,
        onSettingsClick = viewModel::onSettingsClick,
        modifier = modifier,
    )
}
