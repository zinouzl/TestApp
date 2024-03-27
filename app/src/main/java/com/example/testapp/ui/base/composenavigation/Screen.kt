package com.example.testapp.ui.base.composenavigation

import androidx.navigation.NavType

internal sealed class Screen(route: String) : NavigationScreen(route) {

    data object LoginScreen : Screen(route = "login_screen")

    data object PostsScreen : Screen(route = "posts_screen") {

        override fun getArgs(): List<Args> = listOf(
            USER_ID to NavType.IntType,
            USER_EMAIL to NavType.StringType
        ).map { Args(it.first, it.second) }
    }

    internal companion object {
        const val USER_ID = "deviceId"
        const val USER_EMAIL = "userEmail"
    }
}