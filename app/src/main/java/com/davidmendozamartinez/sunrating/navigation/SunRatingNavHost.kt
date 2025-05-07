package com.davidmendozamartinez.sunrating.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.davidmendozamartinez.sunrating.feature.events.EventsRoute
import com.davidmendozamartinez.sunrating.feature.events.eventsScreen

@Composable
fun SunRatingNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = EventsRoute,
        modifier = modifier,
    ) {
        eventsScreen()
    }
}
