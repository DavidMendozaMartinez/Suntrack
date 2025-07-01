package com.davidmendozamartinez.sunrating.feature.places.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.davidmendozamartinez.sunrating.feature.places.model.PlaceItemOptionUiState
import com.davidmendozamartinez.sunrating.feature.places.model.PlaceItemUiState
import com.davidmendozamartinez.sunrating.feature.places.model.preview.buildFakePlaceItemUiState
import com.davidmendozamartinez.sunrating.ui.component.MoreButton
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedDropdownMenu
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedDropdownMenuItem
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedMenuDefaults
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun PlaceItem(
    uiState: PlaceItemUiState,
    onClick: () -> Unit,
    onOptionClick: (PlaceItemOptionUiState) -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource? = null,
) {
    Row(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(),
                onClick = onClick,
            )
            .background(
                color = if (uiState.isSelected) {
                    SunRatingTheme.colorScheme.primary
                } else {
                    Color.Transparent
                },
            )
            .padding(
                horizontal = SunRatingTheme.spacing.space4,
                vertical = SunRatingTheme.spacing.space1,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(
            value = LocalContentColor provides if (uiState.isSelected) {
                SunRatingTheme.colorScheme.onPrimary
            } else {
                SunRatingTheme.colorScheme.onBackground
            },
        ) {
            Text(
                text = uiState.name,
                modifier = Modifier.weight(weight = 1f),
                color = LocalContentColor.current,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = if (uiState.isSelected) {
                    SunRatingTheme.typography.bodyMediumEmphasized
                } else {
                    SunRatingTheme.typography.bodyMedium
                },
            )

            OptionsMenu(
                options = uiState.options,
                onOptionClick = onOptionClick,
                modifier = Modifier.padding(start = SunRatingTheme.spacing.space2),
            )
        }
    }
}

@Composable
private fun OptionsMenu(
    options: ImmutableList<PlaceItemOptionUiState>,
    onOptionClick: (PlaceItemOptionUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        var expanded by remember { mutableStateOf(value = false) }

        MoreButton(
            onClick = { expanded = true },
            enforceMinInteractiveSize = false,
        )

        ThemedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach {
                ThemedDropdownMenuItem(
                    text = it.displayName,
                    onClick = {
                        expanded = false
                        onOptionClick(it)
                    },
                    trailingIcon = {
                        Icon(
                            painter = it.trailingIconPainter,
                            contentDescription = null,
                            modifier = Modifier.size(size = ThemedMenuDefaults.TrailingIconSize),
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PlaceItemPreview(
    @PreviewParameter(provider = PlaceItemPreviewParameterProvider::class) uiState: PlaceItemUiState,
) {
    SunRatingTheme {
        PlaceItem(
            uiState = uiState,
            onClick = {},
            onOptionClick = {},
        )
    }
}

private class PlaceItemPreviewParameterProvider : PreviewParameterProvider<PlaceItemUiState> {
    override val values: Sequence<PlaceItemUiState>
        get() = sequenceOf(
            buildFakePlaceItemUiState(name = LoremIpsum(words = 3).values.first(), isSelected = false),
            buildFakePlaceItemUiState(name = LoremIpsum(words = 9).values.first(), isSelected = true),
        )
}
