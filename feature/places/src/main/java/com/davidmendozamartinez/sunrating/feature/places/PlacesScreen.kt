@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)

package com.davidmendozamartinez.sunrating.feature.places

import android.Manifest
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.davidmendozamartinez.sunrating.feature.places.component.PlacesBottomBar
import com.davidmendozamartinez.sunrating.feature.places.component.content.PlacesContentSuccessState
import com.davidmendozamartinez.sunrating.feature.places.model.PlaceItemOptionUiState
import com.davidmendozamartinez.sunrating.feature.places.model.PlacesBottomBarUiState
import com.davidmendozamartinez.sunrating.feature.places.model.PlacesContentUiState
import com.davidmendozamartinez.sunrating.feature.places.model.PlacesSnackbar
import com.davidmendozamartinez.sunrating.feature.places.model.PlacesUiState
import com.davidmendozamartinez.sunrating.feature.places.model.preview.buildFakePlaceItemUiState
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.component.CloseButton
import com.davidmendozamartinez.sunrating.ui.component.custom.SkylineBackground
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedSnackbar
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedTopAppBar
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import com.davidmendozamartinez.sunrating.ui.extension.navigateToApplicationDetailsSettings
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun PlacesScreen(
    uiState: PlacesUiState,
    onBackClick: () -> Unit,
    onPlaceClick: (String) -> Unit,
    onPlaceOptionClick: (String, PlaceItemOptionUiState) -> Unit,
    onPlaceNameValueChange: (String) -> Unit,
    onCreatePlaceClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
    val locationPermissionState: PermissionState = rememberLocationPermissionState(
        snackbarHostState = snackbarHostState,
        onPermissionGrantedResult = onCreatePlaceClick,
    )

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
                onCreatePlaceClick = {
                    when {
                        locationPermissionState.status.isGranted -> onCreatePlaceClick()
                        else -> locationPermissionState.launchPermissionRequest()
                    }
                },
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { ThemedSnackbar(snackbarData = it) },
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
                    onPlaceOptionClick = onPlaceOptionClick,
                )
            }
        }
    }
}

@Composable
private fun rememberLocationPermissionState(
    snackbarHostState: SnackbarHostState,
    onPermissionGrantedResult: () -> Unit,
): PermissionState {
    val scope: CoroutineScope = rememberCoroutineScope()
    val context: Context = LocalContext.current

    return rememberPermissionState(
        permission = Manifest.permission.ACCESS_COARSE_LOCATION,
        onPermissionResult = { isGranted ->
            if (isGranted) {
                onPermissionGrantedResult()
            } else {
                scope.launch {
                    val result: SnackbarResult = snackbarHostState.showSnackbar(
                        visuals = PlacesSnackbar.LocationPermission.getVisuals(context = context),
                    )
                    if (result == SnackbarResult.ActionPerformed) context.navigateToApplicationDetailsSettings()
                }
            }
        },
    )
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
            onPlaceOptionClick = { _, _ -> },
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
