package com.example.shared.data.repository

import com.example.shared.data.entities.UserEntity
import com.example.shared.data.mapper.Mapper
import com.example.shared.data.services.AuthService
import com.example.shared.domain.model.User
import com.example.shared.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val userMapper: Mapper<UserEntity, User>
) : AuthRepository {

    override suspend fun getUser(id: Int): User {
        return userMapper.toData(authService.getUser(id))
    }
}