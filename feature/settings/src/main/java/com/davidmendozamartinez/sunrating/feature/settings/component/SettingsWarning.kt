package com.davidmendozamartinez.sunrating.feature.settings.component

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.davidmendozamartinez.sunrating.feature.settings.model.SettingsWarningUiState
import com.davidmendozamartinez.sunrating.ui.animation.AnimatedVisibility
import com.davidmendozamartinez.sunrating.ui.component.custom.Banner
import com.davidmendozamartinez.sunrating.ui.component.custom.BannerDefaults
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import com.davidmendozamartinez.sunrating.ui.extension.navigateToAlarmSettings
import com.davidmendozamartinez.sunrating.ui.extension.navigateToNotificationsSettings

@Composable
fun SettingsWarning(
    uiState: SettingsWarningUiState,
    onActionResult: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context: Context = LocalContext.current
    val launcher: ManagedActivityResultLauncher<Intent, ActivityResult> = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { onActionResult() },
    )

    Banner(
        message = uiState.message,
        actionLabel = uiState.actionLabel,
        onActionClick = {
            when (uiState) {
                SettingsWarningUiState.NotificationsPermission -> context.navigateToNotificationsSettings(launcher = launcher)
                SettingsWarningUiState.ExactAlarm -> context.navigateToAlarmSettings(launcher = launcher)
            }
        },
        modifier = modifier,
        iconPainter = uiState.iconPainter,
        colors = BannerDefaults.warningColors(),
    )
}

@Composable
fun AnimatedSettingsWarningVisibility(
    value: SettingsWarningUiState?,
    modifier: Modifier = Modifier,
    content: @Composable AnimatedVisibilityScope.(SettingsWarningUiState) -> Unit,
) {
    AnimatedVisibility(
        value = value,
        modifier = modifier,
        enter = fadeIn(animationSpec = spring(stiffness = Spring.StiffnessLow)) +
                expandVertically(animationSpec = spring(stiffness = Spring.StiffnessLow)),
        exit = fadeOut(animationSpec = spring(stiffness = Spring.StiffnessLow)) +
                shrinkVertically(animationSpec = spring(stiffness = Spring.StiffnessLow)),
        label = "AnimatedSettingsWarningVisibility",
        content = content,
    )
}

@Composable
fun AnimatedSettingsWarningContent(
    targetState: SettingsWarningUiState,
    modifier: Modifier = Modifier,
    content: @Composable AnimatedContentScope.(SettingsWarningUiState) -> Unit,
) {
    AnimatedContent(
        targetState = targetState,
        modifier = modifier,
        transitionSpec = {
            slideInHorizontally(animationSpec = spring(stiffness = Spring.StiffnessLow), initialOffsetX = { it }) togetherWith
                    slideOutHorizontally(animationSpec = spring(stiffness = Spring.StiffnessLow), targetOffsetX = { -it })
        },
        label = "AnimatedSettingsWarningContent",
        content = content,
    )
}

@Preview
@Composable
private fun SettingsWarningPreview(
    @PreviewParameter(provider = SettingsWarningPreviewParameterProvider::class) uiState: SettingsWarningUiState,
) {
    SunRatingTheme {
        SettingsWarning(
            uiState = uiState,
            onActionResult = {},
        )
    }
}

private class SettingsWarningPreviewParameterProvider : PreviewParameterProvider<SettingsWarningUiState> {
    override val values: Sequence<SettingsWarningUiState>
        get() = sequenceOf(
            SettingsWarningUiState.NotificationsPermission,
            SettingsWarningUiState.ExactAlarm,
        )
}
