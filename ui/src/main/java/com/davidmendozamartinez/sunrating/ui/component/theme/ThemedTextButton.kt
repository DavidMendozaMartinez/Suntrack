package com.davidmendozamartinez.sunrating.ui.component.theme

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import com.davidmendozamartinez.sunrating.ui.extension.asDisabled

@Composable
fun ThemedTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = SunRatingTheme.shape.medium,
    colors: ButtonColors = ThemedTextButtonDefaults.primaryColors(),
    contentPadding: PaddingValues = ThemedTextButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = null,
        border = null,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    ) {
        Text(
            text = text,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = SunRatingTheme.typography.bodyMediumEmphasized,
        )
    }
}

@Preview
@Composable
private fun ThemedTextButtonPreview() {
    SunRatingTheme {
        ThemedTextButton(
            text = LoremIpsum(words = 3).values.first(),
            onClick = {},
        )
    }
}

object ThemedTextButtonDefaults {
    @Composable
    fun primaryColors(): ButtonColors = ButtonDefaults.textButtonColors(
        containerColor = Color.Transparent,
        contentColor = SunRatingTheme.colorScheme.primary,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = SunRatingTheme.colorScheme.primary.asDisabled(),
    )

    @Composable
    fun onWarningColors(): ButtonColors = ButtonDefaults.textButtonColors(
        containerColor = Color.Transparent,
        contentColor = SunRatingTheme.colorScheme.onWarning,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = SunRatingTheme.colorScheme.onWarning.asDisabled(),
    )

    val ContentPadding: PaddingValues
        @Composable get() = PaddingValues(
            horizontal = SunRatingTheme.spacing.space4,
            vertical = SunRatingTheme.spacing.space2,
        )
}
