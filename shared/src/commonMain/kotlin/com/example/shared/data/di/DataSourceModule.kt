package com.example.shared.data.di

import com.example.shared.data.base.KtorClient
import com.example.shared.data.services.AuthService
import com.example.shared.data.services.AuthServiceImpl
import com.example.shared.data.services.PostService
import com.example.shared.data.services.PostServiceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single { KtorClient.client }
    single<AuthService> { AuthServiceImpl(get()) }
    single<PostService> { PostServiceImpl(get()) }
}
const val BASE_URL = "https://jsonplaceholder.typicode.com"