package com.davidmendozamartinez.sunrating.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.davidmendozamartinez.sunrating.feature.events.EventsNavigation
import com.davidmendozamartinez.sunrating.feature.events.EventsRoute
import com.davidmendozamartinez.sunrating.feature.events.eventsScreen
import com.davidmendozamartinez.sunrating.feature.places.navigateToPlaces
import com.davidmendozamartinez.sunrating.feature.places.placesScreen

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
        eventsScreen(
            onNavigationEvent = {
                when (it) {
                    is EventsNavigation.Places -> navController.navigateToPlaces()
                    is EventsNavigation.Settings -> Unit // TODO(future)
                }
            },
        )

        placesScreen()
    }
}
