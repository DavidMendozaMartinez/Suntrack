package com.davidmendozamartinez.sunrating.ui.designsystem.custom

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColorScheme(
    val primary: Color = Color.Unspecified,
    val onPrimary: Color = Color.Unspecified,
    val primaryContainer: Color = Color.Unspecified,
    val onPrimaryContainer: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val onSecondary: Color = Color.Unspecified,
    val secondaryContainer: Color = Color.Unspecified,
    val onSecondaryContainer: Color = Color.Unspecified,
    val tertiary: Color = Color.Unspecified,
    val onTertiary: Color = Color.Unspecified,
    val tertiaryContainer: Color = Color.Unspecified,
    val onTertiaryContainer: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val onBackground: Color = Color.Unspecified,
    val surface: Color = Color.Unspecified,
    val onSurface: Color = Color.Unspecified,
    val surfaceLow: Color = Color.Unspecified,
    val onSurfaceLow: Color = Color.Unspecified,
//    val surfaceVariant: Color = Color.Unspecified,
//    val onSurfaceVariant: Color = Color.Unspecified,
//    val surfaceVariantLow: Color = Color.Unspecified,
//    val onSurfaceVariantLow: Color = Color.Unspecified,
    val outline: Color = Color.Unspecified,
    val outlineVariant: Color = Color.Unspecified,
    val success: Color = Color.Unspecified,
    val onSuccess: Color = Color.Unspecified,
    val successContainer: Color = Color.Unspecified,
    val onSuccessContainer: Color = Color.Unspecified,
    val warning: Color = Color.Unspecified,
    val onWarning: Color = Color.Unspecified,
    val warningContainer: Color = Color.Unspecified,
    val onWarningContainer: Color = Color.Unspecified,
    val danger: Color = Color.Unspecified,
    val onDanger: Color = Color.Unspecified,
    val dangerContainer: Color = Color.Unspecified,
    val onDangerContainer: Color = Color.Unspecified,
)

internal val LocalCustomColorScheme = staticCompositionLocalOf { CustomColorScheme() }
