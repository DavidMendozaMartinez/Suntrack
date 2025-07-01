package com.davidmendozamartinez.sunrating.feature.places.component.content

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.davidmendozamartinez.sunrating.feature.places.component.PlaceItem
import com.davidmendozamartinez.sunrating.feature.places.model.PlacesContentUiState
import com.davidmendozamartinez.sunrating.feature.places.model.preview.buildFakePlaceItemUiState
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import kotlinx.collections.immutable.toImmutableList

@Composable
fun PlacesContentSuccessState(
    uiState: PlacesContentUiState.Success,
    onPlaceClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = uiState.items, key = { it.id }) { itemUiState ->
            PlaceItem(
                uiState = itemUiState,
                onClick = { onPlaceClick(itemUiState.id) },
                modifier = Modifier.animateItem(),
            )
        }
    }
}

@Preview
@Composable
private fun PlacesContentSuccessStatePreview(
    @PreviewParameter(provider = PlacesContentSuccessStatePreviewParameterProvider::class) uiState: PlacesContentUiState.Success,
) {
    SunRatingTheme {
        PlacesContentSuccessState(
            uiState = uiState,
            onPlaceClick = {},
        )
    }
}

private class PlacesContentSuccessStatePreviewParameterProvider : PreviewParameterProvider<PlacesContentUiState.Success> {
    override val values: Sequence<PlacesContentUiState.Success>
        get() = sequenceOf(
            PlacesContentUiState.Success(
                items = List(size = 5) {
                    buildFakePlaceItemUiState(id = it.toString(), isSelected = it == 3)
                }.toImmutableList(),
            ),
        )
}
