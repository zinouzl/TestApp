// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.jetbrains.kotlin.multiplatform) apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}


buildscript {
    repositories {
        google()
        mavenCentral()
    }
}
