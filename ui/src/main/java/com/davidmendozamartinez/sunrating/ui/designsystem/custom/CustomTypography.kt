package com.davidmendozamartinez.sunrating.ui.designsystem.custom

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

@Immutable
data class CustomTypography(
    val display: TextStyle = TextStyle.Default,
    val headline: TextStyle = TextStyle.Default,
    val titleLarge: TextStyle = TextStyle.Default,
    val titleMedium: TextStyle = TextStyle.Default,
    val titleSmall: TextStyle = TextStyle.Default,
    val bodyLarge: TextStyle = TextStyle.Default,
    val bodyMedium: TextStyle = TextStyle.Default,
    val bodySmall: TextStyle = TextStyle.Default,
    val displayEmphasized: TextStyle = TextStyle.Default,
    val headlineEmphasized: TextStyle = TextStyle.Default,
    val titleLargeEmphasized: TextStyle = TextStyle.Default,
    val titleMediumEmphasized: TextStyle = TextStyle.Default,
    val titleSmallEmphasized: TextStyle = TextStyle.Default,
    val bodyLargeEmphasized: TextStyle = TextStyle.Default,
    val bodyMediumEmphasized: TextStyle = TextStyle.Default,
    val bodySmallEmphasized: TextStyle = TextStyle.Default,
)

internal val LocalCustomTypography = staticCompositionLocalOf { CustomTypography() }
