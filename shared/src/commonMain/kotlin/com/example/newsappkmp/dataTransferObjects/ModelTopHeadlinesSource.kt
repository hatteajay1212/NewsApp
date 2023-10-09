package com.example.newsappkmp.dataTransferObjects

import kotlinx.serialization.Serializable

@Serializable
data class ModelTopHeadlinesSource (
    val id : String?,
    val name : String?,
    val description : String? = "",
    val url : String? = "",
    val category : String? = "",
    val language : String? = "",
    val country : String? = ""
)