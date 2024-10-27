package com.exemple.presentation.ui.base.composenavigation

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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