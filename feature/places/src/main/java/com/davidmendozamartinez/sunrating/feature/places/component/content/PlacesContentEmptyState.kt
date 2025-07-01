package com.davidmendozamartinez.sunrating.feature.places.component.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme

@Composable
fun PlacesContentEmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .padding(all = SunRatingTheme.spacing.space4),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.state_empty_places),
            contentDescription = null,
            modifier = Modifier.size(size = ImageSize),
        )

        Text(
            text = stringResource(id = R.string.places_state_empty_title),
            modifier = Modifier.padding(top = SunRatingTheme.spacing.space2),
            color = SunRatingTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            style = SunRatingTheme.typography.titleLarge,
        )

        Text(
            text = stringResource(id = R.string.places_state_empty_subtitle),
            color = SunRatingTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            style = SunRatingTheme.typography.bodyMedium,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PlacesContentEmptyStatePreview() {
    SunRatingTheme {
        PlacesContentEmptyState()
    }
}

private val ImageSize: Dp = 200.dp
