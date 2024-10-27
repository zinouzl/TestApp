package com.example.shared.data.services

import com.example.shared.data.entities.PostEntity

interface PostService {

    suspend fun getPostsOfUser(userId: Int): List<PostEntity>
}