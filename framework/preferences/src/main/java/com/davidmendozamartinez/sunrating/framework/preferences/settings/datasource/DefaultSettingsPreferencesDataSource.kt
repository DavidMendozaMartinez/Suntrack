package com.davidmendozamartinez.sunrating.framework.preferences.settings.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.davidmendozamartinez.sunrating.data.settings.datasource.SettingsPreferencesDataSource
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.settings.model.EventAlertSettings
import com.davidmendozamartinez.sunrating.domain.settings.model.EventAlertSettingsDefaults
import com.davidmendozamartinez.sunrating.framework.preferences.settings.model.EventAlertSettingsPreferencesKeys
import com.davidmendozamartinez.sunrating.framework.preferences.settings.model.toEventAlertSettingsPreferencesKeys
import javax.inject.Inject
import kotlin.time.Duration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DefaultSettingsPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : SettingsPreferencesDataSource {
    override suspend fun getEventAlertSettings(eventType: EventType): EventAlertSettings {
        val preferences: Preferences = dataStore.data.first()
        val keys: EventAlertSettingsPreferencesKeys = eventType.toEventAlertSettingsPreferencesKeys()
        return preferences.getEventAlertSettings(keys = keys, eventType = eventType)
    }

    override suspend fun setEventAlertSettings(settings: List<EventAlertSettings>) {
        val settingsByKey = settings.associateBy { it.eventType.toEventAlertSettingsPreferencesKeys() }
        dataStore.edit { preferences ->
            settingsByKey.forEach { (keys, value) -> preferences.setEventAlertSettings(keys = keys, value = value) }
        }
    }

    override fun getEventAlertSettingsFlow(): Flow<List<EventAlertSettings>> {
        val keysByEventType = EventType.entries.associateWith { it.toEventAlertSettingsPreferencesKeys() }
        return dataStore.data.map { preferences ->
            keysByEventType.map { (eventType, keys) -> preferences.getEventAlertSettings(keys = keys, eventType = eventType) }
        }.distinctUntilChanged()
    }

    private fun Preferences.getEventAlertSettings(
        keys: EventAlertSettingsPreferencesKeys,
        eventType: EventType,
    ): EventAlertSettings = EventAlertSettings(
        eventType = eventType,
        isEnabled = get(key = keys.isEnabled)
            ?: EventAlertSettingsDefaults.DefaultIsEnabled,
        advance = get(key = keys.advance)
            ?.let { Duration.parseIsoString(value = it) }
            ?: EventAlertSettingsDefaults.DefaultAdvance,
        qualityThreshold = get(key = keys.qualityThreshold)
            ?: EventAlertSettingsDefaults.DefaultQualityThreshold,
    )

    private fun MutablePreferences.setEventAlertSettings(
        keys: EventAlertSettingsPreferencesKeys,
        value: EventAlertSettings
    ) {
        set(key = keys.isEnabled, value = value.isEnabled)
        set(key = keys.advance, value = value.advance.toIsoString())
        set(key = keys.qualityThreshold, value = value.qualityThreshold)
    }
}
