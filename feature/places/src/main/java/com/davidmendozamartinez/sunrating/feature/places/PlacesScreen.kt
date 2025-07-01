@file:OptIn(ExperimentalMaterial3Api::class)

package com.davidmendozamartinez.sunrating.feature.places

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.davidmendozamartinez.sunrating.feature.places.component.PlacesBottomBar
import com.davidmendozamartinez.sunrating.feature.places.component.content.PlacesContentSuccessState
import com.davidmendozamartinez.sunrating.feature.places.model.PlacesBottomBarUiState
import com.davidmendozamartinez.sunrating.feature.places.model.PlacesContentUiState
import com.davidmendozamartinez.sunrating.feature.places.model.PlacesUiState
import com.davidmendozamartinez.sunrating.feature.places.model.preview.buildFakePlaceItemUiState
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.component.CloseButton
import com.davidmendozamartinez.sunrating.ui.component.custom.SkylineBackground
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedTopAppBar
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun PlacesScreen(
    uiState: PlacesUiState,
    onBackClick: () -> Unit,
    onPlaceClick: (String) -> Unit,
    onPlaceNameValueChange: (String) -> Unit,
    onCreatePlaceClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            ThemedTopAppBar(
                title = stringResource(id = R.string.places_title),
                actions = { CloseButton(onClick = onBackClick) },
            )
        },
        bottomBar = {
            PlacesBottomBar(
                uiState = uiState.bottomBarUiState,
                onPlaceNameValueChange = onPlaceNameValueChange,
                onCreatePlaceClick = onCreatePlaceClick,
                modifier = Modifier,
            )
        },
        containerColor = SunRatingTheme.colorScheme.background,
        contentColor = SunRatingTheme.colorScheme.onBackground,
    ) { contentPadding ->
        SkylineBackground()

        Box(modifier = Modifier.padding(paddingValues = contentPadding)) {
            when (val contentUiState = uiState.contentUiState) {
                is PlacesContentUiState.Loading -> Unit
                is PlacesContentUiState.Success -> PlacesContentSuccessState(
                    uiState = contentUiState,
                    onPlaceClick = onPlaceClick,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PlacesScreenPreview(
    @PreviewParameter(provider = PlacesScreenPreviewParameterProvider::class) uiState: PlacesUiState,
) {
    SunRatingTheme {
        PlacesScreen(
            uiState = uiState,
            onBackClick = {},
            onPlaceClick = {},
            onPlaceNameValueChange = {},
            onCreatePlaceClick = {},
        )
    }
}

private class PlacesScreenPreviewParameterProvider : PreviewParameterProvider<PlacesUiState> {
    override val values: Sequence<PlacesUiState>
        get() = sequenceOf(
            PlacesUiState(
                bottomBarUiState = PlacesBottomBarUiState(),
                contentUiState = PlacesContentUiState.Success(
                    items = List(size = 5) {
                        buildFakePlaceItemUiState(id = it.toString(), isSelected = it == 3)
                    }.toImmutableList(),
                ),
            ),
        )
}
