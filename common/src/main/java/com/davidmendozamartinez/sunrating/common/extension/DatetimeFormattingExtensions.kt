package com.davidmendozamartinez.sunrating.common.extension


import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.format.TextStyle
import java.util.Locale
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalTime

/**
 * Uses java.time for formatting since kotlinx.datetime currently lacks
 * localization support.
 */
fun LocalTime.format(): String = DateTimeFormatter
    .ofLocalizedTime(FormatStyle.SHORT)
    .format(toJavaLocalTime())

fun DayOfWeek.format(
    locale: Locale = Locale.getDefault(),
): String = getDisplayName(TextStyle.SHORT, locale)
