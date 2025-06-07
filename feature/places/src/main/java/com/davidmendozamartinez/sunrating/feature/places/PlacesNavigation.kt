package com.davidmendozamartinez.sunrating.feature.places

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.davidmendozamartinez.sunrating.feature.places.model.PlacesUiState
import kotlinx.serialization.Serializable

@Serializable
object PlacesRoute

sealed interface PlacesNavigation {
    data object Back : PlacesNavigation
}

fun NavController.navigateToPlaces(navOptions: NavOptions? = null) {
    navigate(route = PlacesRoute, navOptions = navOptions)
}

fun NavGraphBuilder.placesScreen(onNavigationEvent: (PlacesNavigation) -> Unit) {
    composable<PlacesRoute>(
        enterTransition = {
            slideIntoContainer(towards = SlideDirection.Up, animationSpec = tween(durationMillis = TRANSITION_DURATION_MILLIS))
        },
        exitTransition = {
            slideOutOfContainer(towards = SlideDirection.Down, animationSpec = tween(durationMillis = TRANSITION_DURATION_MILLIS))
        },
    ) {
        PlacesRoute(onNavigationEvent = onNavigationEvent)
    }
}

@Composable
internal fun PlacesRoute(
    onNavigationEvent: (PlacesNavigation) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlacesViewModel = hiltViewModel(),
) {
    val uiState: PlacesUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navigation: PlacesNavigation? by viewModel.navigation.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = navigation) {
        navigation?.let { onNavigationEvent(it) }
        viewModel.onNavigationEventConsumed()
    }

    PlacesScreen(
        uiState = uiState,
        onBackClick = viewModel::onBackClick,
        onPlaceClick = viewModel::onPlaceClick,
        modifier = modifier,
    )
}

private const val TRANSITION_DURATION_MILLIS: Int = 500
