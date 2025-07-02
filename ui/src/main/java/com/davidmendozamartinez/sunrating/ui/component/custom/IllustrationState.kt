package com.davidmendozamartinez.sunrating.ui.component.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedButton
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme

@Composable
fun IllustrationState(
    painter: Painter,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    action: @Composable (() -> Unit)? = null,
) {
    Column(
        modifier = modifier.padding(all = SunRatingTheme.spacing.space8),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .sizeIn(minHeight = ImageMinHeight, maxWidth = ContentMaxWidth)
                .fillMaxWidth(),
        )

        Text(
            text = title,
            modifier = Modifier
                .padding(top = SunRatingTheme.spacing.space8)
                .widthIn(max = ContentMaxWidth),
            color = SunRatingTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            style = SunRatingTheme.typography.headline,
        )

        Text(
            text = subtitle,
            modifier = Modifier
                .padding(top = SunRatingTheme.spacing.space2)
                .widthIn(max = ContentMaxWidth),
            color = SunRatingTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            style = SunRatingTheme.typography.bodyMedium,
        )

        action?.let {
            Box(
                modifier = Modifier
                    .padding(top = SunRatingTheme.spacing.space8)
                    .widthIn(max = ContentMaxWidth)
                    .fillMaxWidth(),
                content = { action() },
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun IllustrationStatePreview() {
    SunRatingTheme {
        IllustrationState(
            painter = painterResource(id = R.drawable.places_state_empty),
            title = LoremIpsum(words = 3).values.first(),
            subtitle = LoremIpsum(words = 7).values.first(),
            action = {
                ThemedButton(
                    text = LoremIpsum(words = 3).values.first(),
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        )
    }
}

private val ImageMinHeight: Dp = 180.dp
private val ContentMaxWidth: Dp = 500.dp
