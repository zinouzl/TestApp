package com.example.shared.data.mapper

import com.example.shared.data.entities.UserEntity
import com.example.shared.domain.model.User


class UserMapper : Mapper<UserEntity, User> {

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