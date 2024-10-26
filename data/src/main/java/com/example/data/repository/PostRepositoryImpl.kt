package com.example.data.repository

import com.example.data.entities.PostEntity
import com.example.data.mapper.Mapper
import com.example.data.services.PostService
import com.example.domain.model.Post
import com.example.domain.repository.PostRepository

class PostRepositoryImpl(
    private val postService: PostService,
    private val postMapper: Mapper<PostEntity, Post>
) : PostRepository {

    override suspend fun getPostsOfUser(userId: Int): List<Post> {
        return postService.getPostsOfUser(userId)
            .map { postEntity -> postMapper.toData(postEntity) }
    }
}