package com.davidmendozamartinez.sunrating.common.extension

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toKotlinLocalTime
import kotlinx.datetime.toLocalDateTime

fun Instant.toLocalDate(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDate =
    toLocalDateTime(timeZone = timeZone).date

fun Instant.toLocalTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalTime =
    toLocalDateTime(timeZone = timeZone).time

fun Clock.System.today(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDate =
    now().toLocalDate(timeZone = timeZone)

fun LocalDate.isToday(timeZone: TimeZone = TimeZone.currentSystemDefault()): Boolean =
    this == Clock.System.today(timeZone = timeZone)

fun LocalDate.start(timeZone: TimeZone = TimeZone.currentSystemDefault()): Instant =
    LocalDateTime(date = this, time = java.time.LocalTime.MIN.toKotlinLocalTime()).toInstant(timeZone = timeZone)

fun LocalDate.end(timeZone: TimeZone = TimeZone.currentSystemDefault()): Instant =
    LocalDateTime(date = this, time = java.time.LocalTime.MAX.toKotlinLocalTime()).toInstant(timeZone = timeZone)
