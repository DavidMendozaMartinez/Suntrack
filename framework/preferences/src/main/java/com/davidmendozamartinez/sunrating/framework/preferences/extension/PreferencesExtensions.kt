package com.davidmendozamartinez.sunrating.framework.preferences.extension

import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences.Key

fun <T> MutablePreferences.setOrRemove(key: Key<T>, value: T?) {
    if (value != null) {
        set(key = key, value = value)
    } else {
        remove(key = key)
    }
}
