package com.davidmendozamartinez.sunrating.ui.component.custom

import androidx.annotation.IntRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import com.davidmendozamartinez.sunrating.ui.extension.enabled
import com.davidmendozamartinez.sunrating.ui.extension.toImageBitmap

@Composable
fun StarRatingBar(
    value: Float,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: StarRatingBarColors = StarRatingBarDefaults.primaryColors(),
    @IntRange(from = 0) stars: Int = 5,
) {
    Row(
        modifier = modifier.padding(all = SunRatingTheme.spacing.space1),
        horizontalArrangement = Arrangement.spacedBy(space = SunRatingTheme.spacing.space3),
    ) {
        repeat(times = stars) { index ->
            val fillFraction: Float = (value - index).coerceIn(minimumValue = 0f, maximumValue = 1f)
            val color: Color = if (fillFraction > 0f) colors.activeColor else colors.inactiveColor

            Star(
                fillFraction = fillFraction,
                color = color.enabled(enabled = enabled),
            )
        }
    }
}

@Composable
private fun Star(
    fillFraction: Float,
    color: Color,
    modifier: Modifier = Modifier,
) {
    val painter: Painter = painterResource(id = R.drawable.ic_star_filled)

    Canvas(
        modifier = modifier
            .size(size = StarSize)
            .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen),
    ) {
        val base: ImageBitmap = painter.toImageBitmap(
            density = this,
            layoutDirection = layoutDirection,
            size = DpSize(width = StarSize, height = StarSize).toSize(),
        )
        val mask: ImageBitmap = painter.toImageBitmap(
            density = this,
            layoutDirection = layoutDirection,
            size = DpSize(width = StarMaskSize, height = StarMaskSize).toSize(),
        )

        drawImage(image = base, colorFilter = ColorFilter.tint(color = color))

        translate(left = StarStrokeWidth.toPx(), top = StarStrokeWidth.toPx()) {
            clipRect(left = mask.width * fillFraction) {
                drawImage(image = mask, blendMode = BlendMode.Xor)
            }
        }
    }
}

@Preview
@Composable
private fun StarRatingBarPreview() {
    SunRatingTheme {
        StarRatingBar(
            value = 2.5f,
        )
    }
}

object StarRatingBarDefaults {
    @Composable
    fun primaryColors(): StarRatingBarColors = StarRatingBarColors(
        activeColor = SunRatingTheme.colorScheme.warning,
        inactiveColor = SunRatingTheme.colorScheme.primaryContainer,
    )

    @Composable
    fun tertiaryColors(): StarRatingBarColors = StarRatingBarColors(
        activeColor = SunRatingTheme.colorScheme.warning,
        inactiveColor = SunRatingTheme.colorScheme.tertiaryContainer,
    )
}

@Immutable
data class StarRatingBarColors(
    val activeColor: Color,
    val inactiveColor: Color,
)

private val StarSize: Dp = 32.dp
private val StarStrokeWidth: Dp = 6.dp
private val StarMaskSize: Dp = StarSize - StarStrokeWidth * 2
