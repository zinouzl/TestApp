package com.example.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.data.di.dataSourceModule
import com.example.data.di.mapperModule
import com.example.data.di.repositoryModule
import com.example.domain.di.domainModule
import com.example.domain.di.useCaseModule
import com.example.testapp.di.viewModelModule
import com.example.testapp.ui.auth.AuthScreen
import com.example.testapp.ui.auth.AuthViewModel
import com.example.testapp.ui.base.composenavigation.Screen
import com.example.testapp.ui.base.composenavigation.composable
import com.example.testapp.ui.posts.PostViewModel
import com.example.testapp.ui.posts.PostsScreen
import com.example.testapp.ui.theme.TestAppTheme
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(extra = intent.extras)
        }
    }
}

@Composable
fun App(extra: Bundle?) {
    KoinApplication(application = {
        modules(
            dataSourceModule,
            domainModule,
            repositoryModule,
            mapperModule,
            viewModelModule,
            useCaseModule
        )
    }) {
        TestAppTheme {
            SetupNavGraph(
                navController = rememberNavController(),
                arguments = extra
            )
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
                viewModel = koinViewModel<AuthViewModel>(),
                navController = navController
            )
        }

        composable(screen = Screen.PostsScreen) {
            PostsScreen(
                viewModel = koinViewModel<PostViewModel>(),
                navController = navController
            )
        }
    }
}