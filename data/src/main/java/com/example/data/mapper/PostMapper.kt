package com.example.data.mapper

import com.example.data.entities.PostEntity
import com.example.domain.model.Post

class PostMapper : Mapper<PostEntity, Post> {
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