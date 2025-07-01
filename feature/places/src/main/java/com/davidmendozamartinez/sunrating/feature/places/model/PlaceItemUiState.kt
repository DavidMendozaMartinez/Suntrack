package com.davidmendozamartinez.sunrating.feature.places.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import com.davidmendozamartinez.sunrating.domain.place.model.Place
import com.davidmendozamartinez.sunrating.ui.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class PlaceItemUiState(
    val id: String,
    val name: String,
    val isSelected: Boolean,
) {
    val options: ImmutableList<PlaceItemOptionUiState> = PlaceItemOptionUiState.entries.toImmutableList()
}

enum class PlaceItemOptionUiState {
    DELETE;

    val displayName: String
        @Composable get() = when (this) {
            DELETE -> stringResource(id = R.string.places_item_option_delete)
        }

    val trailingIconPainter: Painter
        @Composable get() = when (this) {
            DELETE -> rememberVectorPainter(image = Icons.Outlined.Delete)
        }
}

fun Place.toPlaceItemUiState(isSelected: Boolean): PlaceItemUiState = PlaceItemUiState(
    id = id,
    name = name,
    isSelected = isSelected,
)
