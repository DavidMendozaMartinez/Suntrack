package com.davidmendozamartinez.sunrating.ui.designsystem.custom

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

@Immutable
data class CustomElevation(
    val level0: Dp = Dp.Unspecified,
    val level1: Dp = Dp.Unspecified,
    val level2: Dp = Dp.Unspecified,
    val level3: Dp = Dp.Unspecified,
    val level4: Dp = Dp.Unspecified,
    val level5: Dp = Dp.Unspecified,
)

internal val LocalCustomElevation = staticCompositionLocalOf { CustomElevation() }
