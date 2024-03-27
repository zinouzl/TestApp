package com.example.domain.repository

import com.example.domain.model.User

interface AuthRepository {

    suspend fun getUser(id: Int) : User
}