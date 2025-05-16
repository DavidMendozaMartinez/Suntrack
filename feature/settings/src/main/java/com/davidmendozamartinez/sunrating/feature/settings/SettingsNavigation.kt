package com.davidmendozamartinez.sunrating.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.davidmendozamartinez.sunrating.feature.settings.model.SettingsUiState
import kotlinx.serialization.Serializable

@Serializable
object SettingsRoute

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    navigate(route = SettingsRoute, navOptions = navOptions)
}

fun NavGraphBuilder.settingsScreen() {
    composable<SettingsRoute> {
        SettingsRoute()
    }
}

@Composable
internal fun SettingsRoute(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val uiState: SettingsUiState by viewModel.uiState.collectAsStateWithLifecycle()

    SettingsScreen(
        uiState = uiState,
        onEventAlertEnabledCheckedChange = viewModel::onEventAlertEnabledCheckedChange,
        onEventAlertAdvanceItemClick = viewModel::onEventAlertAdvanceItemClick,
        onEventAlertQualityThresholdValueChange = viewModel::onEventAlertQualityThresholdValueChange,
        modifier = modifier,
    )
}
