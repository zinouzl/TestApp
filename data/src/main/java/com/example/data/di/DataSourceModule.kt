package com.example.data.di

import com.example.data.base.KtorClient
import com.example.data.services.AuthService
import com.example.data.services.AuthServiceImpl
import com.example.data.services.PostService
import com.example.data.services.PostServiceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single { KtorClient.client }
    single<AuthService> { AuthServiceImpl(get()) }
    single<PostService> { PostServiceImpl(get()) }
}
const val BASE_URL = "https://jsonplaceholder.typicode.com"