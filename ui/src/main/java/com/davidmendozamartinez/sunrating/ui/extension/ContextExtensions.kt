package com.davidmendozamartinez.sunrating.ui.extension

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.core.net.toUri

fun Context.navigateToApplicationSettings() {
    try {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, "package:$packageName".toUri())
        startActivity(intent)
    } catch (expected: ActivityNotFoundException) {
        // no-op
    }
}

fun Context.navigateToNotificationsSettings(
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
) {
    try {
        val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
        launcher.launch(intent)
    } catch (expected: ActivityNotFoundException) {
        // no-op
    }
}

fun Context.navigateToAlarmSettings(
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
) {
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM, "package:$packageName".toUri())
            launcher.launch(intent)
        }
    } catch (expected: ActivityNotFoundException) {
        // no-op
    }
}
