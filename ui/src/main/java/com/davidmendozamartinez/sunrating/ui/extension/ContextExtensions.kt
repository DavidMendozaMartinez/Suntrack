package com.davidmendozamartinez.sunrating.ui.extension

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.core.net.toUri

fun Context.navigateToApplicationDetailsSettings() {
    try {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, "package:$packageName".toUri())
        startActivity(intent)
    } catch (expected: ActivityNotFoundException) {
        // no-op
    }
}
