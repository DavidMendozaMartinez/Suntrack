package com.davidmendozamartinez.sunrating.ui.designsystem.custom

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
data class CustomShape(
    val none: CornerBasedShape = RoundedCornerShape(size = 0.dp),
    val extraSmall: CornerBasedShape = RoundedCornerShape(size = 0.dp),
    val small: CornerBasedShape = RoundedCornerShape(size = 0.dp),
    val medium: CornerBasedShape = RoundedCornerShape(size = 0.dp),
    val large: CornerBasedShape = RoundedCornerShape(size = 0.dp),
    val extraLarge: CornerBasedShape = RoundedCornerShape(size = 0.dp),
    val full: CornerBasedShape = RoundedCornerShape(size = 0.dp),
)

internal val LocalCustomShape = staticCompositionLocalOf { CustomShape() }
