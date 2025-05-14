package com.davidmendozamartinez.sunrating.feature.events

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidmendozamartinez.sunrating.common.extension.format
import com.davidmendozamartinez.sunrating.feature.events.model.EventTypeUiState
import com.davidmendozamartinez.sunrating.feature.events.model.EventUiState
import com.davidmendozamartinez.sunrating.feature.events.model.EventsTopAppBarUiState
import com.davidmendozamartinez.sunrating.feature.events.model.EventsUiState
import java.time.DayOfWeek
import java.util.Locale
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.LocalTime

@Composable
internal fun EventsScreen(
    uiState: EventsUiState,
    onCurrentPlaceClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            EventsTopAppBar(
                uiState = uiState.topAppBarUiState,
                onTitleClick = onCurrentPlaceClick,
                onSettingsClick = onSettingsClick,
            )
        },
    ) { contentPadding ->
        Box(modifier = Modifier.padding(paddingValues = contentPadding)) {
            when (uiState) {
                is EventsUiState.Loading -> Unit
                is EventsUiState.Success -> LazyColumn { items(items = uiState.events) { Event(uiState = it) } }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EventsTopAppBar(
    uiState: EventsTopAppBarUiState,
    onTitleClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = onTitleClick),
                contentAlignment = Alignment.CenterStart,
            ) {
                Text(text = (uiState as? EventsTopAppBarUiState.Success)?.title ?: "Add a place")
            }
        },
        modifier = modifier,
        actions = {
            IconButton(onClick = onSettingsClick) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = null,
                )
            }
        },
    )
}

@Composable
private fun Event(
    uiState: EventUiState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.defaultMinSize(minWidth = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = uiState.dayOfWeek.format().uppercase(), fontSize = 18.sp)
            Text(text = uiState.time.format())
        }

        Text(text = uiState.type.name.lowercase().replaceFirstChar { it.titlecase(Locale.getDefault()) })
        Text(text = "${(uiState.quality * 100) / 10}/10")

        Icon(
            imageVector = if (uiState.hasAlarm) Icons.Filled.Notifications else Icons.Outlined.Notifications,
            contentDescription = null,
        )
    }
}

@Preview
@Composable
private fun EventsScreenPreview(
    @PreviewParameter(provider = EventsScreenPreviewParameterProvider::class) uiState: EventsUiState,
) {
    EventsScreen(
        uiState = uiState,
        onCurrentPlaceClick = {},
        onSettingsClick = {},
    )
}

private class EventsScreenPreviewParameterProvider : PreviewParameterProvider<EventsUiState> {
    override val values: Sequence<EventsUiState>
        get() = sequenceOf(
            EventsUiState.Success(
                topAppBarUiState = EventsTopAppBarUiState.Success(title = "Madrid"),
                events = persistentListOf(
                    EventUiState(
                        dayOfWeek = DayOfWeek.MONDAY,
                        time = LocalTime(hour = 7, minute = 20, second = 0),
                        type = EventTypeUiState.SUNRISE,
                        quality = 0.6f,
                        hasAlarm = true,
                    )
                ),
            ),
        )
}
