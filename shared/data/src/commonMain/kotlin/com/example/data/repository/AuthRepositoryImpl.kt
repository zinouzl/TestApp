package com.example.data.repository

import com.example.data.entities.UserEntity
import com.example.data.mapper.Mapper
import com.example.data.services.AuthService
import com.example.domain.model.User
import com.example.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val userMapper: Mapper<UserEntity, User>
) : AuthRepository {

    override suspend fun getUser(id: Int): User {
        return userMapper.toData(authService.getUser(id))
    }
}