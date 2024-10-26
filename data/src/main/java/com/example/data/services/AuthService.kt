package com.example.data.services

import com.example.data.entities.UserEntity

interface AuthService {

    suspend fun getUser(id: Int): UserEntity
}