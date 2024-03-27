package com.example.domain.repository

import com.example.domain.model.Post

interface PostRepository {

    suspend fun getPostsOfUser(userId: Int): List<Post>
}