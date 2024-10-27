package com.example.shared.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    @SerialName("id")
    val id: Int = 0,

    @SerialName("username")
    val userName: String,

    @SerialName("email")
    val email: String,

    @SerialName("website")
    val website: String,
) : Entity