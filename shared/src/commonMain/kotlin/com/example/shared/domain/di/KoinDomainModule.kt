package com.example.shared.domain.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val domainModule = module {
    single<CoroutineDispatcher> { Dispatchers.IO }
}