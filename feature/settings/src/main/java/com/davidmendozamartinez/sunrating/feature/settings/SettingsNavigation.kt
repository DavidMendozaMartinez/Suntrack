package com.davidmendozamartinez.sunrating.feature.settings

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
import com.davidmendozamartinez.sunrating.feature.settings.model.SettingsUiState
import kotlinx.serialization.Serializable

@Serializable
object SettingsRoute

sealed interface SettingsNavigation {
    data object Back : SettingsNavigation
}

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    navigate(route = SettingsRoute, navOptions = navOptions)
}

fun NavGraphBuilder.settingsScreen(onNavigationEvent: (SettingsNavigation) -> Unit) {
    composable<SettingsRoute>(
        enterTransition = {
            slideIntoContainer(
                towards = SlideDirection.Left,
                animationSpec = tween(durationMillis = TRANSITION_DURATION_MILLIS),
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = SlideDirection.Right,
                animationSpec = tween(durationMillis = TRANSITION_DURATION_MILLIS),
            )
        },
    ) {
        SettingsRoute(onNavigationEvent = onNavigationEvent)
    }
}

@Composable
internal fun SettingsRoute(
    onNavigationEvent: (SettingsNavigation) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val uiState: SettingsUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navigation: SettingsNavigation? by viewModel.navigation.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = navigation) {
        navigation?.let { onNavigationEvent(it) }
        viewModel.onNavigationEventConsumed()
    }

    SettingsScreen(
        uiState = uiState,
        onBackClick = viewModel::onBackClick,
        onNotificationsPermissionResult = viewModel::onNotificationsPermissionResult,
        onWarningActionResult = viewModel::onWarningActionResult,
        onEventAlertEnableCheckedChange = viewModel::onEventAlertEnableCheckedChange,
        onEventAlertAdvanceItemClick = viewModel::onEventAlertAdvanceItemClick,
        onEventAlertQualityThresholdValueChange = viewModel::onEventAlertQualityThresholdValueChange,
        onSaveClick = viewModel::onSaveClick,
        modifier = modifier,
    )
}

private const val TRANSITION_DURATION_MILLIS: Int = 400
