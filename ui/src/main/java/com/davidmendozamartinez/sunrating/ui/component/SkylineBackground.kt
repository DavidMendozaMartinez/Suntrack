package com.davidmendozamartinez.sunrating.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme

@Composable
fun SkylineBackground(
    modifier: Modifier = Modifier,
    color: Color = Color(color = 0xFFFFEAF2).copy(alpha = 0.4f),
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = color)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_skyline),
            contentDescription = null,
            modifier = Modifier
                .align(alignment = Alignment.BottomStart)
                .alpha(alpha = 0.1f),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun SkylineBackgroundPreview() {
    SunRatingTheme {
        SkylineBackground()
    }
}
