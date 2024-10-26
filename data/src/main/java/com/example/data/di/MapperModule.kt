package com.example.data.di

import com.example.data.entities.PostEntity
import com.example.data.entities.UserEntity
import com.example.data.mapper.Mapper
import com.example.data.mapper.PostMapper
import com.example.data.mapper.UserMapper
import com.example.domain.model.Post
import com.example.domain.model.User
import org.koin.dsl.module

val mapperModule = module {
    single<Mapper<PostEntity, Post>> { PostMapper() }
    single<Mapper<UserEntity, User>> { UserMapper() }
}