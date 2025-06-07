package com.davidmendozamartinez.sunrating.ui.extension

import androidx.compose.ui.graphics.Color

fun Color.enabled(enabled: Boolean): Color = copy(alpha = if (enabled) ALPHA_ENABLED else ALPHA_DISABLED)

fun Color.asDisabled(): Color = copy(alpha = ALPHA_DISABLED)

private const val ALPHA_ENABLED = 1.00f
private const val ALPHA_DISABLED = 0.38f
