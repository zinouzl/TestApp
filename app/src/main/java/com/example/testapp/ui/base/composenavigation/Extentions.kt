package com.example.testapp.ui.base.composenavigation

import android.os.Bundle
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.composable(
    screen: NavigationScreen,
    defaultArgs: Bundle? = null,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = screen.hostPath,
        arguments = screen.hostArgs(defaultArgs),
        content = content
    )
}