package com.example.data.repository

import com.example.data.mapper.PostMapper
import com.example.data.services.PostService
import com.example.domain.model.Post
import com.example.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postService: PostService,
    private val postMapper: PostMapper
) : PostRepository {

    override suspend fun getPostsOfUser(userId: Int): List<Post> {
        return postService.getPostsOfUser(userId)
            .map { postEntity -> postMapper.toData(postEntity) }
    }
}