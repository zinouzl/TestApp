package com.example.shared.data.di

import com.example.shared.data.entities.PostEntity
import com.example.shared.data.entities.UserEntity
import com.example.shared.data.mapper.Mapper
import com.example.shared.data.mapper.PostMapper
import com.example.shared.data.mapper.UserMapper
import com.example.shared.domain.model.Post
import com.example.shared.domain.model.User
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val POST_MAPPER_QUALIFIER = "POST_MAPPER"
const val USER_MAPPER_QUALIFIER = "USER_MAPPER"

val mapperModule = module {
    single<Mapper<PostEntity, Post>>(named(POST_MAPPER_QUALIFIER)) { PostMapper() }
    single<Mapper<UserEntity, User>>(named(USER_MAPPER_QUALIFIER)) { UserMapper() }
}