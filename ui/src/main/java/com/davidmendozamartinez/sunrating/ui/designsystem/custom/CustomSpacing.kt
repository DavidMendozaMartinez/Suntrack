package com.davidmendozamartinez.sunrating.ui.designsystem.custom

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

@Immutable
data class CustomSpacing(private val base: Dp = Dp.Unspecified) {
    val space1: Dp = base
    val space2: Dp = base.times(other = 2)
    val space3: Dp = base.times(other = 3)
    val space4: Dp = base.times(other = 4)
    val space5: Dp = base.times(other = 5)
    val space6: Dp = base.times(other = 6)
    val space7: Dp = base.times(other = 7)
    val space8: Dp = base.times(other = 8)
    val space9: Dp = base.times(other = 9)
    val space10: Dp = base.times(other = 10)
    val space12: Dp = base.times(other = 12)
    val space14: Dp = base.times(other = 14)
    val space16: Dp = base.times(other = 16)
    val space18: Dp = base.times(other = 18)
    val space20: Dp = base.times(other = 20)
}

internal val LocalCustomSpacing = staticCompositionLocalOf { CustomSpacing() }
