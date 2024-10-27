package com.example.shared.domain.repository

import com.example.shared.domain.model.User

interface AuthRepository {

    suspend fun getUser(id: Int) : User
}