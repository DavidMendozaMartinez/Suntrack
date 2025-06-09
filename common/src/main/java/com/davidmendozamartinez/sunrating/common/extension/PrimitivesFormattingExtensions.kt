package com.davidmendozamartinez.sunrating.common.extension

import java.util.Locale

fun Float.format(locale: Locale = Locale.getDefault()): String = String.format(
    locale = locale,
    format = "%.1f",
    args = arrayOf(this),
)
