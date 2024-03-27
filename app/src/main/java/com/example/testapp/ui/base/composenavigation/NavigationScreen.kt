package com.example.testapp.ui.base.composenavigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import androidx.navigation.navArgument

abstract class NavigationScreen(private val route: String) {

    val hostPath
        get() = StringBuilder().apply {
            append(route)
            getArgs().forEach {
                append("/")
                append("{${it.name}}")
            }
        }.toString()

    fun hostArgs(defaultArgument: Bundle? = null) = buildList {
        this@NavigationScreen.getArgs().forEach {
            add(
                navArgument(it.name) {
                    type = it.type
                    when (it.type) {
                        NavType.StringType -> defaultArgument?.getString(it.name)?.let { value ->
                            defaultValue = value
                        }

                        NavType.IntType -> defaultArgument?.getInt(it.name)?.let { value ->
                            defaultValue = value
                        }
                    }
                }
            )
        }
    }

    open fun getArgs(): List<Args> = listOf()

    fun navigationPath(vararg args: NavigationArgs): String = Uri.Builder().apply {
        path(route)
        getArgs().forEach { arg ->
            appendPath(args.find { it.name == arg.name }?.value)
        }
    }.build().toString()
}

data class Args(val name: String, val type: NavType<*>)

data class NavigationArgs(val name: String, val value: String)