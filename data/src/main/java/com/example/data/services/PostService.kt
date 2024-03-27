package com.example.data.services

import com.example.data.entities.PostEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {

    @GET("posts")
    suspend fun getPostsOfUser(@Query("userId") userId: Int): List<PostEntity>
}