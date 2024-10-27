package com.example.shared

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.shared.data.di.dataSourceModule
import com.example.shared.data.di.mapperModule
import com.example.shared.data.di.repositoryModule
import com.example.shared.domain.di.domainModule
import com.example.shared.domain.di.useCaseModule
import com.example.shared.presentation.SetupNavGraph
import com.example.shared.presentation.di.viewModelModule
import com.example.shared.presentation.ui.theme.TestAppTheme
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