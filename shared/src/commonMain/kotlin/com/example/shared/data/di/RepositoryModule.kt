package com.example.shared.data.di

import com.example.shared.data.repository.AuthRepositoryImpl
import com.example.shared.data.repository.PostRepositoryImpl
import com.example.shared.domain.repository.AuthRepository
import com.example.shared.domain.repository.PostRepository
import org.koin.dsl.module


val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<PostRepository> { PostRepositoryImpl(get(), get()) }
}