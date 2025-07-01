package com.davidmendozamartinez.sunrating.feature.places.model

import android.content.Context
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import com.davidmendozamartinez.sunrating.ui.R

sealed interface PlacesSnackbar {
    fun getVisuals(context: Context): SnackbarVisuals

    data object LocationPermission : PlacesSnackbar {
        override fun getVisuals(context: Context): SnackbarVisuals = object : SnackbarVisuals {
            override val message: String = context.getString(R.string.snackbar_permission_location_message)
            override val actionLabel: String = context.getString(R.string.snackbar_permission_location_action)
            override val withDismissAction: Boolean = true
            override val duration: SnackbarDuration = SnackbarDuration.Indefinite
        }
    }
}
