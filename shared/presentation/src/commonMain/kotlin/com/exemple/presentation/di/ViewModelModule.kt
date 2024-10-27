package com.exemple.presentation.di

import com.exemple.presentation.ui.auth.AuthViewModel
import com.exemple.presentation.ui.base.composenavigation.Profile
import com.exemple.presentation.ui.posts.PostViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { (profile: Profile) -> PostViewModel(profile, get()) }
}