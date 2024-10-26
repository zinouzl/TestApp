package com.example.testapp.ui.base.composenavigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Profile(val userId: Int, val userEmail: String) : Parcelable

@Serializable
internal sealed interface Screen {

    @Serializable
    data object LoginScreen : Screen

    @Serializable
    data class PostsScreen(
        val profile: Profile
    ) : Screen
}