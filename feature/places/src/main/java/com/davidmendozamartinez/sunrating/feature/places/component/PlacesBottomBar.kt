package com.davidmendozamartinez.sunrating.feature.places.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.davidmendozamartinez.sunrating.feature.places.model.PlaceNameTextFieldUiState
import com.davidmendozamartinez.sunrating.feature.places.model.PlacesBottomBarUiState
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedButton
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedTextField
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme

@Composable
fun PlacesBottomBar(
    uiState: PlacesBottomBarUiState,
    onPlaceNameValueChange: (String) -> Unit,
    onCreatePlaceClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = PlacesBottomBarDefaults.shape,
    windowInsets: WindowInsets = PlacesBottomBarDefaults.windowInsets
) {
    val softwareKeyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier
            .clip(shape = shape)
            .background(color = SunRatingTheme.colorScheme.background)
            .windowInsetsPadding(insets = windowInsets)
            .padding(all = SunRatingTheme.spacing.space4),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PlaceNameTextField(
            uiState = uiState.placeNameTextFieldUiState,
            isDoneEnabled = uiState.isCreateButtonEnabled,
            onValueChange = onPlaceNameValueChange,
            onDoneClick = {
                softwareKeyboardController?.hide()
                onCreatePlaceClick()
            },
            modifier = Modifier.weight(weight = 1f),
        )

        ThemedButton(
            text = stringResource(id = R.string.places_button_create),
            onClick = {
                softwareKeyboardController?.hide()
                onCreatePlaceClick()
            },
            modifier = Modifier.padding(start = SunRatingTheme.spacing.space4),
            enabled = uiState.isCreateButtonEnabled,
        )
    }
}

@Composable
private fun PlaceNameTextField(
    uiState: PlaceNameTextFieldUiState,
    isDoneEnabled: Boolean,
    onValueChange: (String) -> Unit,
    onDoneClick: KeyboardActionScope.() -> Unit,
    modifier: Modifier = Modifier,
) {
    ThemedTextField(
        value = uiState.value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = uiState.isEnabled,
        placeholder = stringResource(id = R.string.places_field_name_placeholder),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(onDone = onDoneClick.takeIf { isDoneEnabled }),
        singleLine = true,
    )
}

@Preview
@Composable
private fun PlacesBottomBarPreview(
    @PreviewParameter(provider = PlacesBottomBarPreviewParameterProvider::class) uiState: PlacesBottomBarUiState,
) {
    SunRatingTheme {
        PlacesBottomBar(
            uiState = uiState,
            onPlaceNameValueChange = {},
            onCreatePlaceClick = {},
        )
    }
}

private class PlacesBottomBarPreviewParameterProvider : PreviewParameterProvider<PlacesBottomBarUiState> {
    override val values: Sequence<PlacesBottomBarUiState>
        get() = sequenceOf(
            PlacesBottomBarUiState(
                placeNameTextFieldUiState = PlaceNameTextFieldUiState(value = ""),
                isCreateButtonEnabled = false,
            ),
            PlacesBottomBarUiState(
                placeNameTextFieldUiState = PlaceNameTextFieldUiState(value = LoremIpsum(words = 3).values.first()),
                isCreateButtonEnabled = true,
            ),
        )
}

object PlacesBottomBarDefaults {
    val windowInsets: WindowInsets
        @Composable get() = WindowInsets.navigationBars.union(insets = WindowInsets.ime)

    val shape: Shape
        @Composable get() = SunRatingTheme.shape.large.copy(
            bottomStart = ZeroCornerSize,
            bottomEnd = ZeroCornerSize,
        )
}
