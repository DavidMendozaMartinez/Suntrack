package com.davidmendozamartinez.sunrating.ui.component.theme

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme

@Composable
fun ThemedSnackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    actionOnNewLine: Boolean = true,
    colors: ThemedSnackbarColors = ThemedSnackbarDefaults.inverseSurfaceColors(),
) {
    Snackbar(
        snackbarData = snackbarData,
        modifier = modifier,
        actionOnNewLine = actionOnNewLine,
        shape = SunRatingTheme.shape.medium,
        containerColor = colors.containerColor,
        contentColor = colors.contentColor,
        actionColor = colors.actionColor,
        actionContentColor = colors.actionContentColor,
        dismissActionContentColor = colors.dismissActionContentColor,
    )
}

@Preview
@Composable
private fun ThemedSnackbarPreview() {
    SunRatingTheme {
        val snackbarData = object : SnackbarData {
            override val visuals: SnackbarVisuals = object : SnackbarVisuals {
                override val message: String = LoremIpsum(words = 7).values.first()
                override val actionLabel: String = LoremIpsum(words = 2).values.first()
                override val withDismissAction: Boolean = true
                override val duration: SnackbarDuration = SnackbarDuration.Indefinite
            }

            override fun dismiss() {}

            override fun performAction() {}
        }

        ThemedSnackbar(snackbarData = snackbarData)
    }
}

object ThemedSnackbarDefaults {
    @Composable
    fun inverseSurfaceColors(): ThemedSnackbarColors = ThemedSnackbarColors(
        containerColor = SunRatingTheme.colorScheme.inverseSurface,
        contentColor = SunRatingTheme.colorScheme.inverseOnSurface,
        actionColor = SunRatingTheme.colorScheme.primaryContainer,
        actionContentColor = SunRatingTheme.colorScheme.primaryContainer,
        dismissActionContentColor = SunRatingTheme.colorScheme.inverseOnSurface,
    )
}

@Immutable
data class ThemedSnackbarColors(
    val containerColor: Color,
    val contentColor: Color,
    val actionColor: Color,
    val actionContentColor: Color,
    val dismissActionContentColor: Color,
)
