package com.davidmendozamartinez.sunrating.feature.places.component.content

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.component.custom.IllustrationState
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme

@Composable
fun PlacesContentEmptyState(modifier: Modifier = Modifier) {
    IllustrationState(
        painter = painterResource(id = R.drawable.places_state_empty),
        title = stringResource(id = R.string.places_state_empty_title),
        subtitle = stringResource(id = R.string.places_state_empty_subtitle),
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PlacesContentEmptyStatePreview() {
    SunRatingTheme {
        PlacesContentEmptyState()
    }
}
