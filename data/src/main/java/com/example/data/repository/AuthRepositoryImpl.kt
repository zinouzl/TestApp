package com.example.data.repository

import com.example.data.mapper.UserMapper
import com.example.data.services.AuthService
import com.example.domain.model.User
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val userMapper: UserMapper
) : AuthRepository {

    override suspend fun getUser(id: Int): User {
        return userMapper.toData(authService.getUser(id))
    }
}