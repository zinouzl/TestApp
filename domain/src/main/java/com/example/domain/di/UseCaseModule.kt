package com.example.domain.di

import com.example.domain.usecase.GetUserPostsUseCase
import com.example.domain.usecase.GetUserUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetUserUseCase(get(), get()) }
    factory { GetUserPostsUseCase(get(), get()) }
}