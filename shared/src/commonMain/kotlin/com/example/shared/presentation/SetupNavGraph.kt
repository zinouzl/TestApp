package com.example.shared.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.shared.presentation.ui.auth.AuthScreen
import com.example.shared.presentation.ui.auth.AuthViewModel
import com.example.shared.presentation.ui.base.composenavigation.CustomNavType
import com.example.shared.presentation.ui.base.composenavigation.Profile
import com.example.shared.presentation.ui.base.composenavigation.Screen
import com.example.shared.presentation.ui.posts.PostViewModel
import com.example.shared.presentation.ui.posts.PostsScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.typeOf

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.LoginScreen) {
        composable<Screen.LoginScreen> {
            AuthScreen(
                viewModel = koinViewModel<AuthViewModel>(),
                navController = navController
            )
        }

        composable<Screen.PostsScreen>(
            typeMap = mapOf(
                typeOf<Profile>() to CustomNavType<Profile>(
                    Profile.serializer()
                )
            )
        ) {
            val profile = it.toRoute<Screen.PostsScreen>().profile
            PostsScreen(
                viewModel = koinViewModel<PostViewModel>(
                    parameters = {
                        parametersOf(
                            profile
                        )
                    }
                ),
                navController = navController
            )
        }
    }
}