package com.example.shared.data.services

import com.example.shared.data.entities.UserEntity

interface AuthService {

    suspend fun getUser(id: Int): UserEntity
}