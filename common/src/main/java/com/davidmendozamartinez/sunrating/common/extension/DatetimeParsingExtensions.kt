package com.davidmendozamartinez.sunrating.common.extension

import kotlin.time.Duration.Companion.days
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime

fun Instant.toLocalDate(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDate =
    toLocalDateTime(timeZone = timeZone).date

fun Instant.toLocalTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalTime =
    toLocalDateTime(timeZone = timeZone).time

fun Clock.System.today(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDate =
    now().toLocalDate(timeZone = timeZone)

fun Clock.System.tomorrow(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDate =
    (now() + 1.days).toLocalDate(timeZone = timeZone)

fun LocalDate.startOfDay(timeZone: TimeZone = TimeZone.currentSystemDefault()): Instant =
    atStartOfDayIn(timeZone = timeZone)
