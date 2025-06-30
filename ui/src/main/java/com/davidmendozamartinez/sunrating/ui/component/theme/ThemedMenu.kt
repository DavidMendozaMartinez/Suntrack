package com.davidmendozamartinez.sunrating.ui.component.theme

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import com.davidmendozamartinez.sunrating.ui.extension.asDisabled

@Composable
fun ThemedDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    offset: DpOffset = DpOffset(x = 0.dp, y = 0.dp),
    scrollState: ScrollState = rememberScrollState(),
    properties: PopupProperties = PopupProperties(focusable = true),
    containerColor: Color = SunRatingTheme.colorScheme.surface,
    content: @Composable ColumnScope.() -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        offset = offset,
        scrollState = scrollState,
        properties = properties,
        shape = SunRatingTheme.shape.medium,
        containerColor = containerColor,
        tonalElevation = SunRatingTheme.elevation.level0,
        shadowElevation = SunRatingTheme.elevation.level2,
        border = null,
        content = content,
    )
}

@Composable
fun ThemedDropdownMenuItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    colors: MenuItemColors = ThemedMenuDefaults.onSurfaceColors(),
    contentPadding: PaddingValues = ThemedMenuDefaults.DropdownMenuItemContentPadding,
    interactionSource: MutableInteractionSource? = null,
) {
    DropdownMenuItem(
        text = {
            Text(
                text = text,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = SunRatingTheme.typography.bodyMedium,
            )
        },
        onClick = onClick,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        enabled = enabled,
        colors = colors,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ThemedMenuPreview() {
    SunRatingTheme {
        var expanded by remember { mutableStateOf(value = false) }

        Box(modifier = Modifier.size(size = 200.dp)) {
            IconButton(onClick = { expanded = true }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }

            ThemedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                repeat(times = 2) {
                    ThemedDropdownMenuItem(
                        text = LoremIpsum(words = 2).values.first(),
                        onClick = { expanded = false },
                    )
                }
            }
        }
    }
}

object ThemedMenuDefaults {
    @Composable
    fun onSurfaceColors(): MenuItemColors = MenuDefaults.itemColors(
        textColor = SunRatingTheme.colorScheme.onSurface,
        leadingIconColor = SunRatingTheme.colorScheme.onSurface,
        trailingIconColor = SunRatingTheme.colorScheme.onSurface,
        disabledTextColor = SunRatingTheme.colorScheme.onSurface.asDisabled(),
        disabledLeadingIconColor = SunRatingTheme.colorScheme.onSurface.asDisabled(),
        disabledTrailingIconColor = SunRatingTheme.colorScheme.onSurface.asDisabled(),
    )

    val DropdownMenuItemContentPadding: PaddingValues
        @Composable get() = PaddingValues(
            horizontal = SunRatingTheme.spacing.space4,
            vertical = SunRatingTheme.spacing.space2,
        )
}
