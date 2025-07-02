package com.davidmendozamartinez.sunrating.feature.events.state

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.component.custom.IllustrationState
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedButton
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme

@Composable
fun EventsNoCurrentPlaceState(
    onCreatePlaceClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IllustrationState(
        painter = painterResource(id = R.drawable.events_state_no_current_place),
        title = stringResource(id = R.string.events_state_no_current_place_title),
        subtitle = stringResource(id = R.string.events_state_no_current_place_subtitle),
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        action = {
            ThemedButton(
                text = stringResource(id = R.string.events_state_no_current_place_button),
                onClick = onCreatePlaceClick,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun EventsNoCurrentPlaceStatePreview() {
    SunRatingTheme {
        EventsNoCurrentPlaceState(
            onCreatePlaceClick = {},
        )
    }
}
