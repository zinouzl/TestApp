package com.exemple.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.exemple.presentation.ui.auth.AuthScreen
import com.exemple.presentation.ui.auth.AuthViewModel
import com.exemple.presentation.ui.base.composenavigation.CustomNavType
import com.exemple.presentation.ui.base.composenavigation.Profile
import com.exemple.presentation.ui.base.composenavigation.Screen
import com.exemple.presentation.ui.posts.PostViewModel
import com.exemple.presentation.ui.posts.PostsScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.typeOf

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.LoginScreen) {
        composable<Screen.LoginScreen> {
            LocalFocusManager.current.clearFocus()
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
            LocalFocusManager.current.clearFocus()
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