package com.davidmendozamartinez.sunrating.ui.component.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedTextButton
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedTextButtonDefaults
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme

@Composable
fun ActionRequired(
    message: String,
    actionLabel: String,
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconPainter: Painter? = null,
    colors: ActionRequiredColors = ActionRequiredDefaults.warningColors(),
    contentPadding: PaddingValues = ActionRequiredDefaults.ContentPadding,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = SunRatingTheme.shape.large)
            .background(color = colors.containerColor)
            .padding(paddingValues = contentPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        iconPainter?.let {
            Icon(
                painter = it,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = SunRatingTheme.spacing.space4)
                    .size(size = IconSize),
                tint = colors.iconColor,
            )
        }

        Text(
            text = message,
            modifier = Modifier.weight(weight = 1f),
            color = colors.contentColor,
            style = SunRatingTheme.typography.bodyMedium,
        )

        ThemedTextButton(
            text = actionLabel,
            onClick = onActionClick,
            modifier = Modifier.padding(start = SunRatingTheme.spacing.space2),
            colors = colors.actionColors,
        )
    }
}

@Preview
@Composable
private fun ActionRequiredPreview() {
    SunRatingTheme {
        ActionRequired(
            message = LoremIpsum(words = 9).values.first(),
            actionLabel = LoremIpsum(words = 2).values.first(),
            onActionClick = {},
            iconPainter = rememberVectorPainter(image = Icons.Outlined.ErrorOutline),
        )
    }
}

object ActionRequiredDefaults {
    @Composable
    fun warningColors(): ActionRequiredColors = ActionRequiredColors(
        containerColor = SunRatingTheme.colorScheme.warningContainer,
        iconColor = SunRatingTheme.colorScheme.onWarning,
        contentColor = SunRatingTheme.colorScheme.onWarningContainer,
        actionColors = ThemedTextButtonDefaults.onWarningColors(),
    )

    val ContentPadding: PaddingValues
        @Composable get() = PaddingValues(
            horizontal = SunRatingTheme.spacing.space4,
            vertical = SunRatingTheme.spacing.space2,
        )
}

@Immutable
data class ActionRequiredColors(
    val containerColor: Color,
    val iconColor: Color,
    val contentColor: Color,
    val actionColors: ButtonColors,
)

private val IconSize: Dp = 24.dp
