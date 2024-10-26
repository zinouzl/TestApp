package com.example.data.di

import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.PostRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.PostRepository
import org.koin.dsl.module


val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<PostRepository> { PostRepositoryImpl(get(), get()) }
}