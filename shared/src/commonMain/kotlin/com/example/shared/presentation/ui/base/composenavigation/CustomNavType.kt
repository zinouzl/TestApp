package com.example.shared.presentation.ui.base.composenavigation

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

class CustomNavType<T : Any>(private val serializer: KSerializer<T>) :
    NavType<T>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getString(key)?.let { json ->
            Json.decodeFromString(serializer, json)
        }
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString(serializer, value)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, Json.encodeToString(serializer, value))
    }
}