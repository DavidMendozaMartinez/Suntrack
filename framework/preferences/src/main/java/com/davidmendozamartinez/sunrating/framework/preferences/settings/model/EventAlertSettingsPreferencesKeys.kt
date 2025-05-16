package com.davidmendozamartinez.sunrating.framework.preferences.settings.model

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.davidmendozamartinez.sunrating.domain.event.model.EventType

data class EventAlertSettingsPreferencesKeys(
    val isEnabled: Preferences.Key<Boolean>,
    val advance: Preferences.Key<String>,
    val qualityThreshold: Preferences.Key<Float>,
)

fun EventType.toEventAlertSettingsPreferencesKeys(): EventAlertSettingsPreferencesKeys = EventAlertSettingsPreferencesKeys(
    isEnabled = booleanPreferencesKey(name = "${prefix}_alert_is_enabled"),
    advance = stringPreferencesKey(name = "${prefix}_alert_advance"),
    qualityThreshold = floatPreferencesKey(name = "${prefix}_alert_quality_threshold"),
)

private val EventType.prefix: String
    get() = when (this) {
        EventType.SUNRISE -> "sunrise"
        EventType.SUNSET -> "sunset"
    }
