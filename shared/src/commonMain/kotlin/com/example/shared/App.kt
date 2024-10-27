package com.example.shared

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.data.di.dataSourceModule
import com.example.data.di.mapperModule
import com.example.data.di.repositoryModule
import com.example.domain.di.domainModule
import com.example.domain.di.useCaseModule
import com.exemple.presentation.SetupNavGraph
import com.exemple.presentation.di.viewModelModule
import com.exemple.presentation.ui.theme.TestAppTheme
import org.koin.compose.KoinApplication

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