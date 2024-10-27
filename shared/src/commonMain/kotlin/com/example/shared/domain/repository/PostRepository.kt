package com.example.shared.domain.repository

import com.example.shared.domain.model.Post

interface PostRepository {

    suspend fun getPostsOfUser(userId: Int): List<Post>
}