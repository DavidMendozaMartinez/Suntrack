package com.davidmendozamartinez.sunrating.feature.settings.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import com.davidmendozamartinez.sunrating.ui.R
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class AdvanceSettingsUiState(
    val selected: AdvanceUiState,
) {
    val items: ImmutableList<AdvanceUiState> = AdvanceUiState.entries.toImmutableList()
}

enum class AdvanceUiState(val duration: Duration) {
    FIVE_MINUTES(duration = 5.minutes),
    TEN_MINUTES(duration = 10.minutes),
    THIRTY_MINUTES(duration = 30.minutes),
    ONE_HOUR(duration = 1.hours);

    @Composable
    fun format(): String = when {
        duration < 1.hours -> pluralStringResource(
            id = R.plurals.format_duration_minutes,
            count = duration.inWholeMinutes.toInt(),
            formatArgs = arrayOf(duration.inWholeMinutes),
        )

        else -> pluralStringResource(
            id = R.plurals.format_duration_hours,
            count = duration.inWholeHours.toInt(),
            formatArgs = arrayOf(duration.inWholeHours),
        )
    }

    companion object {
        fun from(duration: Duration): AdvanceUiState? = entries.find { it.duration == duration }
    }
}
