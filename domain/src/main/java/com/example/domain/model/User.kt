package com.example.domain.model

data class User(
    val id: Int = 0,
    val userName: String,
    val email: String,
    val website: String
) : DomainData