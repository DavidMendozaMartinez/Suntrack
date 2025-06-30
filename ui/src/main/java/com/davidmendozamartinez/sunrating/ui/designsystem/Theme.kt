package com.davidmendozamartinez.sunrating.ui.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.davidmendozamartinez.sunrating.ui.designsystem.custom.CustomColorScheme
import com.davidmendozamartinez.sunrating.ui.designsystem.custom.CustomElevation
import com.davidmendozamartinez.sunrating.ui.designsystem.custom.CustomShape
import com.davidmendozamartinez.sunrating.ui.designsystem.custom.CustomSpacing
import com.davidmendozamartinez.sunrating.ui.designsystem.custom.CustomTypography
import com.davidmendozamartinez.sunrating.ui.designsystem.custom.LocalCustomColorScheme
import com.davidmendozamartinez.sunrating.ui.designsystem.custom.LocalCustomElevation
import com.davidmendozamartinez.sunrating.ui.designsystem.custom.LocalCustomShape
import com.davidmendozamartinez.sunrating.ui.designsystem.custom.LocalCustomSpacing
import com.davidmendozamartinez.sunrating.ui.designsystem.custom.LocalCustomTypography

@Composable
fun SunRatingTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalCustomColorScheme provides customColorScheme,
        LocalCustomTypography provides customTypography,
        LocalCustomShape provides customShape,
        LocalCustomSpacing provides customSpacing,
        LocalCustomElevation provides customElevation,
        content = content
    )
}

object SunRatingTheme {
    val colorScheme: CustomColorScheme
        @Composable
        get() = LocalCustomColorScheme.current

    val typography: CustomTypography
        @Composable
        get() = LocalCustomTypography.current

    val shape: CustomShape
        @Composable
        get() = LocalCustomShape.current

    val spacing: CustomSpacing
        @Composable
        get() = LocalCustomSpacing.current

    val elevation: CustomElevation
        @Composable
        get() = LocalCustomElevation.current
}
