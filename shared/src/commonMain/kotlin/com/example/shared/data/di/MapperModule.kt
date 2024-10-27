package com.example.shared.data.di

import com.example.shared.data.entities.PostEntity
import com.example.shared.data.entities.UserEntity
import com.example.shared.data.mapper.Mapper
import com.example.shared.data.mapper.PostMapper
import com.example.shared.domain.model.Post
import com.example.shared.domain.model.User
import org.koin.dsl.module

val mapperModule = module {
    single<Mapper<PostEntity, Post>> { PostMapper() }
    single<Mapper<UserEntity, User>> { com.example.shared.data.mapper.UserMapper() }
}