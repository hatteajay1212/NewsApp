package com.example.newsappkmp.dataTransferObjects

import kotlinx.serialization.Serializable

@Serializable
data class ModelTopHeadlinesSource (
    val id : String?,
    val name : String?
)