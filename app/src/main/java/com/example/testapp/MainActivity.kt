package com.example.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.data.di.dataSourceModule
import com.example.data.di.mapperModule
import com.example.data.di.repositoryModule
import com.example.domain.di.domainModule
import com.example.domain.di.useCaseModule
import com.example.testapp.di.viewModelModule
import com.example.testapp.ui.auth.AuthScreen
import com.example.testapp.ui.auth.AuthViewModel
import com.example.testapp.ui.base.composenavigation.CustomNavType
import com.example.testapp.ui.base.composenavigation.Profile
import com.example.testapp.ui.base.composenavigation.Screen
import com.example.testapp.ui.posts.PostViewModel
import com.example.testapp.ui.posts.PostsScreen
import com.example.testapp.ui.theme.TestAppTheme
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
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
                navController = rememberNavController()
            )
        }
    }
}

@Composable
private fun SetupNavGraph(
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
                typeOf<Profile>() to CustomNavType(
                    Profile::class.java,
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