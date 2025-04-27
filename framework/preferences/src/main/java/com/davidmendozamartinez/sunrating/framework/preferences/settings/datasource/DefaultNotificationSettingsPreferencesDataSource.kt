package com.davidmendozamartinez.sunrating.framework.preferences.settings.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.davidmendozamartinez.sunrating.data.settings.datasource.NotificationSettingsPreferencesDataSource
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.settings.model.EventAlertNotificationSettings
import com.davidmendozamartinez.sunrating.domain.settings.model.EventAlertNotificationSettingsDefaults
import com.davidmendozamartinez.sunrating.framework.preferences.extension.setOrRemove
import com.davidmendozamartinez.sunrating.framework.preferences.settings.model.EventAlertNotificationSettingsPreferencesKeys
import com.davidmendozamartinez.sunrating.framework.preferences.settings.model.toEventAlertNotificationSettingsPreferencesKeys
import javax.inject.Inject
import kotlin.time.Duration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DefaultNotificationSettingsPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : NotificationSettingsPreferencesDataSource {
    override suspend fun getEventAlertNotificationSettings(eventType: EventType): EventAlertNotificationSettings {
        val preferences: Preferences = dataStore.data.first()
        val keys: EventAlertNotificationSettingsPreferencesKeys = eventType.toEventAlertNotificationSettingsPreferencesKeys()
        return preferences.getEventAlertNotificationSettings(keys = keys)
    }

    override suspend fun setEventAlertNotificationSettings(
        eventType: EventType,
        settings: EventAlertNotificationSettings,
    ) {
        val keys: EventAlertNotificationSettingsPreferencesKeys = eventType.toEventAlertNotificationSettingsPreferencesKeys()
        dataStore.edit { preferences -> preferences.setEventAlertNotificationSettings(keys = keys, value = settings) }
    }

    override fun getEventAlertNotificationSettingsFlow(eventType: EventType): Flow<EventAlertNotificationSettings> {
        val keys: EventAlertNotificationSettingsPreferencesKeys = eventType.toEventAlertNotificationSettingsPreferencesKeys()
        return dataStore.data.map { preferences -> preferences.getEventAlertNotificationSettings(keys = keys) }
    }

    private fun Preferences.getEventAlertNotificationSettings(
        keys: EventAlertNotificationSettingsPreferencesKeys,
    ): EventAlertNotificationSettings = EventAlertNotificationSettings(
        advance = get(key = keys.advance)
            ?.let { Duration.parseIsoString(value = it) }
            ?: EventAlertNotificationSettingsDefaults.DefaultAdvance,
        qualityThreshold = get(key = keys.qualityThreshold)
            ?: EventAlertNotificationSettingsDefaults.DefaultQualityThreshold,
    )

    private fun MutablePreferences.setEventAlertNotificationSettings(
        keys: EventAlertNotificationSettingsPreferencesKeys,
        value: EventAlertNotificationSettings
    ) {
        set(key = keys.advance, value = value.advance.toIsoString())
        setOrRemove(key = keys.qualityThreshold, value = value.qualityThreshold)
    }
}
