package com.example.data.mapper

import com.example.data.entities.UserEntity
import com.example.domain.model.User
import javax.inject.Inject


class UserMapper @Inject constructor() : Mapper<UserEntity, User> {

    override fun toData(entity: UserEntity): User {
        return entity.run {
            User(
                id = id,
                userName = userName,
                email = email,
                website = website
            )
        }
    }

    override fun toRaw(data: User): UserEntity {
        return data.run {
            UserEntity(
                id = id,
                userName = userName,
                email = email,
                website = website
            )
        }
    }

}