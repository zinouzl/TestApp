package com.example.testapp.ui.base.composenavigation

import android.os.Bundle
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions

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

fun NavController.navigate(directions: NavDirections, builder: NavOptionsBuilder.() -> Unit) {
    navigate(directions.actionId, directions.arguments, navOptions(builder))
}