package com.davidmendozamartinez.sunrating

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.davidmendozamartinez.sunrating.navigation.SunRatingNavHost
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SunRatingTheme {
                val navController: NavHostController = rememberNavController()
                SunRatingNavHost(navController = navController)
            }
        }
    }
}
