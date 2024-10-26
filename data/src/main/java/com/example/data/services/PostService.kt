package com.example.data.services

import com.example.data.entities.PostEntity

interface PostService {

    suspend fun getPostsOfUser(userId: Int): List<PostEntity>
}