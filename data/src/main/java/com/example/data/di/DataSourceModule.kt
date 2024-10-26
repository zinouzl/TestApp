package com.example.data.di

import com.example.data.services.AuthService
import com.example.data.services.PostService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit

private val json = Json { ignoreUnknownKeys = true }

private fun provideRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()

private fun provideAuthService(retrofit: Retrofit): AuthService =
    retrofit.create(AuthService::class.java)

private fun providesPostService(retrofit: Retrofit): PostService =
    retrofit.create(PostService::class.java)

val dataSourceModule = module {
    single { provideRetrofit() }
    single { provideAuthService(get()) }
    single { providesPostService(get()) }
}
const val BASE_URL = "https://jsonplaceholder.typicode.com"