package com.example.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.testapp.ui.auth.AuthScreen
import com.example.testapp.ui.auth.AuthViewModel
import com.example.testapp.ui.base.composenavigation.Screen
import com.example.testapp.ui.base.composenavigation.composable
import com.example.testapp.ui.posts.PostViewModel
import com.example.testapp.ui.posts.PostsScreen
import com.example.testapp.ui.theme.TestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAppTheme {
                SetupNavGraph(
                    navController = rememberNavController(),
                    arguments = intent.extras
                )
            }
        }
    }
}

@Composable
private fun SetupNavGraph(
    navController: NavHostController,
    arguments: Bundle?
) {
    NavHost(navController = navController, startDestination = Screen.LoginScreen.hostPath) {
        composable(screen = Screen.LoginScreen, defaultArgs = arguments) {
            AuthScreen(
                viewModel = hiltViewModel<AuthViewModel>(),
                navController = navController
            )
        }

        composable(screen = Screen.PostsScreen) {
            PostsScreen(
                viewModel = hiltViewModel<PostViewModel>(),
                navController = navController
            )
        }
    }
}