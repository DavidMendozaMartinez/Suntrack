package com.davidmendozamartinez.sunrating.feature.places

import androidx.compose.runtime.Composable
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

fun NavController.navigateToPlaces(navOptions: NavOptions? = null) {
    navigate(route = PlacesRoute, navOptions = navOptions)
}

fun NavGraphBuilder.placesScreen() {
    composable<PlacesRoute> {
        PlacesRoute()
    }
}

@Composable
internal fun PlacesRoute(
    modifier: Modifier = Modifier,
    viewModel: PlacesViewModel = hiltViewModel(),
) {
    val uiState: PlacesUiState by viewModel.uiState.collectAsStateWithLifecycle()

    PlacesScreen(
        uiState = uiState,
        onPlaceClick = viewModel::onPlaceClick,
        onDeletePlaceClick = viewModel::onDeletePlaceClick,
        onNameValueChange = viewModel::onNameValueChange,
        onLatitudeValueChange = viewModel::onLatitudeValueChange,
        onLongitudeValueChange = viewModel::onLongitudeValueChange,
        onLocateClick = viewModel::onLocateClick,
        onCreateClick = viewModel::onCreateClick,
        modifier = modifier,
    )
}
