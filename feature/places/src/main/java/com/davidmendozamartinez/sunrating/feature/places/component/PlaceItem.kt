package com.davidmendozamartinez.sunrating.feature.places.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.davidmendozamartinez.sunrating.feature.places.model.PlaceItemUiState
import com.davidmendozamartinez.sunrating.feature.places.model.preview.buildFakePlaceItemUiState
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme

@Composable
internal fun PlaceItem(
    uiState: PlaceItemUiState,
    onClick: () -> Unit,
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
                vertical = SunRatingTheme.spacing.space3,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = uiState.name,
            modifier = Modifier.weight(weight = 1f),
            color = if (uiState.isSelected) {
                SunRatingTheme.colorScheme.onPrimary
            } else {
                SunRatingTheme.colorScheme.onBackground
            },
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = if (uiState.isSelected) {
                SunRatingTheme.typography.bodyMediumEmphasized
            } else {
                SunRatingTheme.typography.bodyMedium
            },
        )
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
