package com.davidmendozamartinez.sunrating.ui.component.theme

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import com.davidmendozamartinez.sunrating.ui.extension.asDisabled

@Composable
fun ThemedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = SunRatingTheme.shape.full,
    colors: ButtonColors = ThemedButtonDefaults.primaryColors(),
    elevation: ButtonElevation? = ThemedButtonDefaults.buttonElevation(),
    contentPadding: PaddingValues = ThemedButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
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
private fun ThemedButtonPreview() {
    SunRatingTheme {
        ThemedButton(
            text = LoremIpsum(words = 3).values.first(),
            onClick = {},
        )
    }
}

object ThemedButtonDefaults {
    @Composable
    fun primaryColors(): ButtonColors = ButtonDefaults.buttonColors(
        containerColor = SunRatingTheme.colorScheme.primary,
        contentColor = SunRatingTheme.colorScheme.onPrimary,
        disabledContainerColor = SunRatingTheme.colorScheme.primary.asDisabled(),
        disabledContentColor = SunRatingTheme.colorScheme.onPrimary,
    )

    @Composable
    fun buttonElevation(): ButtonElevation = ButtonDefaults.buttonElevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        focusedElevation = 0.dp,
        hoveredElevation = 1.dp,
        disabledElevation = 0.dp,
    )

    val ContentPadding: PaddingValues
        @Composable get() = PaddingValues(
            horizontal = SunRatingTheme.spacing.space4,
            vertical = SunRatingTheme.spacing.space2,
        )
}
