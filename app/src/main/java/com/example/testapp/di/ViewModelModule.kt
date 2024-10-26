package com.example.testapp.di

import com.example.testapp.ui.auth.AuthViewModel
import com.example.testapp.ui.posts.PostViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { PostViewModel(get(), get()) }
}