package com.example.shared.domain.di

import com.example.shared.domain.usecase.GetUserPostsUseCase
import com.example.shared.domain.usecase.GetUserUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetUserUseCase(get(), get()) }
    factory { GetUserPostsUseCase(get(), get()) }
}