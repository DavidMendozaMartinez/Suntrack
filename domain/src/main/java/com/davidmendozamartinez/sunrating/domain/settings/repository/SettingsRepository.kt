package com.davidmendozamartinez.sunrating.domain.settings.repository

import com.davidmendozamartinez.sunrating.domain.settings.model.EventAlertSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun setEventAlertSettings(settings: List<EventAlertSettings>)

    fun getEventAlertSettingsFlow(): Flow<List<EventAlertSettings>>
}
