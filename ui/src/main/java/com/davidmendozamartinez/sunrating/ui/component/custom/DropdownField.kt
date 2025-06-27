package com.davidmendozamartinez.sunrating.ui.component.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import com.davidmendozamartinez.sunrating.ui.extension.enabled

@Composable
fun DropdownField(
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: DropdownFieldColors = DropdownFieldDefaults.surfaceColors(),
    contentPadding: PaddingValues = DropdownFieldDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null
) {
    Row(
        modifier = modifier
            .clip(shape = SunRatingTheme.shape.medium)
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(),
                enabled = enabled,
                role = Role.DropdownList,
                onClick = onClick
            )
            .background(color = colors.containerColor.enabled(enabled = enabled))
            .padding(paddingValues = contentPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = value,
            modifier = Modifier.weight(weight = 1f, fill = false),
            color = colors.contentColor.enabled(enabled = enabled),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = SunRatingTheme.typography.bodyLarge,
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            modifier = Modifier
                .padding(start = SunRatingTheme.spacing.space1)
                .size(size = IconSize),
            tint = colors.contentColor.enabled(enabled = enabled),
        )
    }
}

@Preview
@Composable
private fun DropdownFieldPreview() {
    SunRatingTheme {
        DropdownField(
            value = LoremIpsum(words = 7).values.first(),
            onClick = {},
        )
    }
}

object DropdownFieldDefaults {
    @Composable
    fun surfaceColors(): DropdownFieldColors = DropdownFieldColors(
        containerColor = SunRatingTheme.colorScheme.surface,
        contentColor = SunRatingTheme.colorScheme.onSurface,
    )

    val ContentPadding: PaddingValues
        @Composable get() = PaddingValues(
            horizontal = SunRatingTheme.spacing.space4,
            vertical = SunRatingTheme.spacing.space2,
        )
}

@Immutable
data class DropdownFieldColors(
    val containerColor: Color,
    val contentColor: Color,
)

private val IconSize: Dp = 24.dp
