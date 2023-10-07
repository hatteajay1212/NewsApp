package com.example.newsappkmp.dataSource.remoteDataSource

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NewsApiClient {

    companion object {

        fun getApiClient(): HttpClient {
            return HttpClient {
                install(Logging) {
                    level = LogLevel.ALL
                }

                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }
            }
        }
    }
}