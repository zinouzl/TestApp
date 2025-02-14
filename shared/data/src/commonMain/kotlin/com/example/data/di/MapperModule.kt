package com.example.data.di

import com.example.data.entities.PostEntity
import com.example.data.entities.UserEntity
import com.example.data.mapper.Mapper
import com.example.data.mapper.PostMapper
import com.example.data.mapper.UserMapper
import com.example.domain.model.Post
import com.example.domain.model.User
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val POST_MAPPER_QUALIFIER = "POST_MAPPER_QUALIFIER"
const val USER_MAPPER_QUALIFIER = "USER_MAPPER_QUALIFIER"

val mapperModule = module {
    single<Mapper<PostEntity, Post>>(named(POST_MAPPER_QUALIFIER)) { PostMapper() }
    single<Mapper<UserEntity, User>>(named(USER_MAPPER_QUALIFIER)) { UserMapper() }
}