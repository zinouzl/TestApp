package com.example.shared.presentation.ui.base.composenavigation

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable

@Serializable
data class Profile(val userId: Int, val userEmail: String) {
    override fun toString(): String {
        return Json.encodeToString(this)
    }
}

@Serializable
internal sealed interface Screen {

    @Serializable
    data object LoginScreen : Screen

    @Serializable
    data class PostsScreen(
        val profile: Profile
    ) : Screen
}