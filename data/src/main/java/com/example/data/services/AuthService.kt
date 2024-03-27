package com.example.data.services

import com.example.data.entities.UserEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthService {

    @GET("Users/{id}")
    suspend fun getUser(@Path("id") id: Int): UserEntity
}