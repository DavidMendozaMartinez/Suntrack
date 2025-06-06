package com.davidmendozamartinez.sunrating.ui.designsystem

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import com.davidmendozamartinez.sunrating.ui.designsystem.custom.CustomShape

internal val customShape = CustomShape(
    none = RoundedCornerShape(size = 0.dp),
    extraSmall = RoundedCornerShape(size = 4.dp),
    small = RoundedCornerShape(size = 8.dp),
    medium = RoundedCornerShape(size = 16.dp),
    large = RoundedCornerShape(size = 24.dp),
    extraLarge = RoundedCornerShape(size = 32.dp),
    full = CircleShape,
)
