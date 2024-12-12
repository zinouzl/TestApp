package com.example.shared.data.di

import com.example.shared.data.repository.AuthRepositoryImpl
import com.example.shared.data.repository.PostRepositoryImpl
import com.example.shared.domain.repository.AuthRepository
import com.example.shared.domain.repository.PostRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module


val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get(named(USER_MAPPER_QUALIFIER))) }
    single<PostRepository> { PostRepositoryImpl(get(), get(named(POST_MAPPER_QUALIFIER))) }
}