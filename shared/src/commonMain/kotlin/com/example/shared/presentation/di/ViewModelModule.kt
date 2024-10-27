package com.example.shared.presentation.di

import com.example.shared.presentation.ui.auth.AuthViewModel
import com.example.shared.presentation.ui.base.composenavigation.Profile
import com.example.shared.presentation.ui.posts.PostViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { (profile: Profile) -> PostViewModel(profile, get()) }
}