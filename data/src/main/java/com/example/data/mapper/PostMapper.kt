package com.example.data.mapper

import com.example.data.entities.PostEntity
import com.example.domain.model.Post
import javax.inject.Inject

class PostMapper @Inject constructor() : Mapper<PostEntity, Post> {
    override fun toData(entity: PostEntity): Post {
        return entity.run {
            Post(
                userId = userId,
                id = id,
                title = title,
                body = body
            )
        }
    }

    override fun toRaw(data: Post): PostEntity {
        return data.run {
            PostEntity(
                userId = userId,
                id = id,
                title = title,
                body = body
            )
        }
    }
}