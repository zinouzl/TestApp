package com.example.shared.data.services

import com.example.shared.data.base.BaseService
import com.example.shared.data.entities.UserEntity
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod

internal class AuthServiceImpl(client: HttpClient): AuthService, BaseService(client) {

    override suspend fun getUser(id: Int): UserEntity {
        return request<UserEntity>(endPoint = "Users/$id", method = HttpMethod.Get)
    }
}