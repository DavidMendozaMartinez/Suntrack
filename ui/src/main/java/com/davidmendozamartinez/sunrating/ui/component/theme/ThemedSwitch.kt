package com.davidmendozamartinez.sunrating.ui.component.theme

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import com.davidmendozamartinez.sunrating.ui.extension.asDisabled

@Composable
fun ThemedSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    thumbContent: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    colors: SwitchColors = ThemedSwitchDefaults.primaryColors(),
    interactionSource: MutableInteractionSource? = null,
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        thumbContent = thumbContent,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource,
    )
}

@Preview
@Composable
private fun ThemedSwitchPreview() {
    SunRatingTheme {
        var checked: Boolean by remember { mutableStateOf(value = true) }

        ThemedSwitch(
            checked = checked,
            onCheckedChange = { checked = it },
        )
    }
}

object ThemedSwitchDefaults {
    @Composable
    fun primaryColors(): SwitchColors = SwitchDefaults.colors(
        checkedThumbColor = SunRatingTheme.colorScheme.surface,
        checkedTrackColor = SunRatingTheme.colorScheme.primary,
        checkedBorderColor = Color.Transparent,
        checkedIconColor = SunRatingTheme.colorScheme.primary,
        uncheckedThumbColor = SunRatingTheme.colorScheme.surface,
        uncheckedTrackColor = SunRatingTheme.colorScheme.primaryContainer,
        uncheckedBorderColor = Color.Transparent,
        uncheckedIconColor = SunRatingTheme.colorScheme.primaryContainer,
        disabledCheckedThumbColor = SunRatingTheme.colorScheme.surface.asDisabled(),
        disabledCheckedTrackColor = SunRatingTheme.colorScheme.primary.asDisabled(),
        disabledCheckedBorderColor = Color.Transparent,
        disabledCheckedIconColor = SunRatingTheme.colorScheme.primary.asDisabled(),
        disabledUncheckedThumbColor = SunRatingTheme.colorScheme.surface.asDisabled(),
        disabledUncheckedTrackColor = SunRatingTheme.colorScheme.primaryContainer.asDisabled(),
        disabledUncheckedBorderColor = Color.Transparent,
        disabledUncheckedIconColor = SunRatingTheme.colorScheme.primaryContainer.asDisabled(),
    )

    @Composable
    fun tertiaryColors(): SwitchColors = SwitchDefaults.colors(
        checkedThumbColor = SunRatingTheme.colorScheme.surface,
        checkedTrackColor = SunRatingTheme.colorScheme.tertiary,
        checkedBorderColor = Color.Transparent,
        checkedIconColor = SunRatingTheme.colorScheme.tertiary,
        uncheckedThumbColor = SunRatingTheme.colorScheme.surface,
        uncheckedTrackColor = SunRatingTheme.colorScheme.tertiaryContainer,
        uncheckedBorderColor = Color.Transparent,
        uncheckedIconColor = SunRatingTheme.colorScheme.tertiaryContainer,
        disabledCheckedThumbColor = SunRatingTheme.colorScheme.surface.asDisabled(),
        disabledCheckedTrackColor = SunRatingTheme.colorScheme.tertiary.asDisabled(),
        disabledCheckedBorderColor = Color.Transparent,
        disabledCheckedIconColor = SunRatingTheme.colorScheme.tertiary.asDisabled(),
        disabledUncheckedThumbColor = SunRatingTheme.colorScheme.surface.asDisabled(),
        disabledUncheckedTrackColor = SunRatingTheme.colorScheme.tertiaryContainer.asDisabled(),
        disabledUncheckedBorderColor = Color.Transparent,
        disabledUncheckedIconColor = SunRatingTheme.colorScheme.tertiaryContainer.asDisabled(),
    )
}
