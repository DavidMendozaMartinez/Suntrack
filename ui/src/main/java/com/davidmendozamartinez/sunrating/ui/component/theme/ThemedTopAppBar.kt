@file:OptIn(ExperimentalMaterial3Api::class)

package com.davidmendozamartinez.sunrating.ui.component.theme

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme

@Composable
fun ThemedTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    expandedHeight: Dp = ThemedTopAppBarDefaults.ContainerHeight,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                style = SunRatingTheme.typography.titleMedium,
            )
        },
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        expandedHeight = expandedHeight,
        windowInsets = windowInsets,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = SunRatingTheme.colorScheme.onBackground,
            titleContentColor = SunRatingTheme.colorScheme.onBackground,
            actionIconContentColor = SunRatingTheme.colorScheme.onBackground,
        ),
        scrollBehavior = scrollBehavior,
    )
}

@Preview(showSystemUi = false, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ThemedTopAppBarPreview() {
    SunRatingTheme {
        ThemedTopAppBar(
            title = LoremIpsum(words = 3).values.first(),
            navigationIcon = {
                IconButton(onClick = {}) { Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null) }
            },
            actions = {
                IconButton(onClick = {}) { Icon(imageVector = Icons.Outlined.Settings, contentDescription = null) }
            },
        )
    }
}

private object ThemedTopAppBarDefaults {
    val ContainerHeight = 72.dp
}
