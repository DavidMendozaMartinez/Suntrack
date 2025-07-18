@file:OptIn(ExperimentalMaterial3Api::class)

package com.davidmendozamartinez.sunrating.ui.component.theme

import androidx.annotation.IntRange
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import com.davidmendozamartinez.sunrating.ui.extension.asDisabled

@Composable
fun ThemedSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onValueChangeFinished: (() -> Unit)? = null,
    colors: SliderColors = ThemedSliderDefaults.primaryColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    @IntRange(from = 0) steps: Int = 0,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
) {
    Slider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        onValueChangeFinished = onValueChangeFinished,
        colors = colors,
        interactionSource = interactionSource,
        steps = steps,
        thumb = {
            SliderDefaults.Thumb(
                interactionSource = interactionSource,
                colors = colors,
                enabled = enabled,
                thumbSize = DpSize(width = ThumbSize, height = ThumbSize)
            )
        },
        track = { sliderState ->
            SliderDefaults.Track(
                sliderState = sliderState,
                modifier = Modifier.height(height = TrackHeight),
                enabled = enabled,
                colors = colors,
                drawStopIndicator = null,
                drawTick = { _, _ -> },
                thumbTrackGapSize = ThumbTrackGapSize,
                trackInsideCornerSize = TrackInsideCornerSize,
            )
        },
        valueRange = valueRange,
    )
}

@Preview
@Composable
private fun ThemedSliderPreview() {
    SunRatingTheme {
        var sliderValue: Float by remember { mutableFloatStateOf(value = 0.5f) }

        ThemedSlider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
        )
    }
}

object ThemedSliderDefaults {
    @Composable
    fun primaryColors(): SliderColors = SliderDefaults.colors(
        thumbColor = SunRatingTheme.colorScheme.primary,
        activeTrackColor = SunRatingTheme.colorScheme.primary,
        activeTickColor = SunRatingTheme.colorScheme.primaryContainer,
        inactiveTrackColor = SunRatingTheme.colorScheme.primaryContainer,
        inactiveTickColor = SunRatingTheme.colorScheme.primary,
        disabledThumbColor = SunRatingTheme.colorScheme.primary.asDisabled(),
        disabledActiveTrackColor = SunRatingTheme.colorScheme.primary.asDisabled(),
        disabledActiveTickColor = SunRatingTheme.colorScheme.primaryContainer.asDisabled(),
        disabledInactiveTrackColor = SunRatingTheme.colorScheme.primaryContainer.asDisabled(),
        disabledInactiveTickColor = SunRatingTheme.colorScheme.primary.asDisabled()
    )

    @Composable
    fun tertiaryColors(): SliderColors = SliderDefaults.colors(
        thumbColor = SunRatingTheme.colorScheme.tertiary,
        activeTrackColor = SunRatingTheme.colorScheme.tertiary,
        activeTickColor = SunRatingTheme.colorScheme.tertiaryContainer,
        inactiveTrackColor = SunRatingTheme.colorScheme.tertiaryContainer,
        inactiveTickColor = SunRatingTheme.colorScheme.tertiary,
        disabledThumbColor = SunRatingTheme.colorScheme.tertiary.asDisabled(),
        disabledActiveTrackColor = SunRatingTheme.colorScheme.tertiary.asDisabled(),
        disabledActiveTickColor = SunRatingTheme.colorScheme.tertiaryContainer.asDisabled(),
        disabledInactiveTrackColor = SunRatingTheme.colorScheme.tertiaryContainer.asDisabled(),
        disabledInactiveTickColor = SunRatingTheme.colorScheme.tertiary.asDisabled()
    )
}

private val ThumbSize: Dp = 20.dp
private val TrackHeight: Dp = 6.dp
private val ThumbTrackGapSize: Dp = 0.dp
private val TrackInsideCornerSize: Dp = 0.dp
