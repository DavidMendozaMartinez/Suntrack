package com.davidmendozamartinez.sunrating.feature.settings.model

import com.davidmendozamartinez.sunrating.common.extension.format
import com.davidmendozamartinez.sunrating.domain.event.model.Event

data class QualityThresholdSettingsUiState(
    val value: Float,
) {
    val formattedValue: String = value.format()
    val scale: Int = Event.QUALITY_SCALE
    val valueRange: ClosedFloatingPointRange<Float> = 0f..scale.toFloat()
    val sliderSteps: Int = scale * 2 - 1
}
