package com.example.shared.data.repository

import com.example.shared.data.entities.PostEntity
import com.example.shared.data.mapper.Mapper
import com.example.shared.data.services.PostService
import com.example.shared.domain.model.Post
import com.example.shared.domain.repository.PostRepository

class PostRepositoryImpl(
    private val postService: PostService,
    private val postMapper: Mapper<PostEntity, Post>
) : PostRepository {

    override suspend fun getPostsOfUser(userId: Int): List<Post> {
        return postService.getPostsOfUser(userId)
            .map { postEntity -> postMapper.toData(postEntity) }
    }
}