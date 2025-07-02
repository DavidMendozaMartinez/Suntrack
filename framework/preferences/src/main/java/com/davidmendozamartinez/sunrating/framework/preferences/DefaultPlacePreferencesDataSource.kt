package com.davidmendozamartinez.sunrating.framework.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.davidmendozamartinez.sunrating.data.place.datasource.PlacePreferencesDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DefaultPlacePreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : PlacePreferencesDataSource {
    private object PreferencesKeys {
        val CURRENT_PLACE_ID = stringPreferencesKey(name = "current_place_id")
    }

    override suspend fun getCurrentPlaceId(): String? {
        val preferences: Preferences = dataStore.data.first()
        return preferences[PreferencesKeys.CURRENT_PLACE_ID]
    }

    override suspend fun setCurrentPlaceId(placeId: String?) {
        dataStore.edit { preferences ->
            placeId
                ?.let { preferences[PreferencesKeys.CURRENT_PLACE_ID] = it }
                ?: preferences.remove(key = PreferencesKeys.CURRENT_PLACE_ID)
        }
    }

    override fun getCurrentPlaceIdFlow(): Flow<String?> =
        dataStore.data
            .map { preferences -> preferences[PreferencesKeys.CURRENT_PLACE_ID] }
            .distinctUntilChanged()
}
