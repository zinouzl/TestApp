package com.example.data.base

import com.example.data.di.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.headers
import io.ktor.util.StringValues

internal abstract class BaseService(
    val client: HttpClient
) {

    suspend inline fun <reified T> request(
        endPoint: String,
        method: HttpMethod,
        queryPairs: List<Pair<String, String>> = emptyList(),
        additionalHeaders: StringValues = StringValues.Empty,
        crossinline block: HttpRequestBuilder.() -> Unit = {}
    ): T {
        return client.request(
            urlString = buildString {
                append(BASE_URL)
                append("/")
                append(endPoint)
            },
            block = {
                this.method = method
                headers {
                    if (!additionalHeaders.isEmpty()) appendAll(additionalHeaders)
                }
                url {
                    queryPairs.forEach {
                        parameters.append(it.first, it.second)
                    }
                }
                block()
            }
        ).body()
    }
}