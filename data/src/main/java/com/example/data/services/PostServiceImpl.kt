package com.example.data.services

import com.example.data.base.BaseService
import com.example.data.entities.PostEntity
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod

internal class PostServiceImpl(client: HttpClient) : PostService, BaseService(client) {

    override suspend fun getPostsOfUser(userId: Int): List<PostEntity> {
        return request(
            endPoint = "posts",
            method = HttpMethod.Get,
            queryPairs = listOf(Pair("userId", "$userId"))
        )
    }
}