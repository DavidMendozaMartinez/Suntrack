package com.davidmendozamartinez.sunrating.ui.extension

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

fun Painter.toImageBitmap(
    density: Density,
    layoutDirection: LayoutDirection,
    size: Size,
): ImageBitmap {
    val image = ImageBitmap(width = size.width.toInt(), height = size.height.toInt())
    val canvas = Canvas(image = image)

    CanvasDrawScope().draw(
        density = density,
        layoutDirection = layoutDirection,
        canvas = canvas,
        size = size,
    ) {
        draw(size = size)
    }

    return image
}
