package com.davidmendozamartinez.sunrating.common.extension


import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalDate

/**
 * Uses java.time for formatting since kotlinx.datetime currently lacks
 * localization support.
 */
fun LocalDate.format(): String = DateTimeFormatter
    .ofLocalizedDate(FormatStyle.SHORT).also { println(this) }
    .format(toJavaLocalDate())

fun LocalTime.format(): String = LocalTime(hour = hour, minute = minute).toString()
