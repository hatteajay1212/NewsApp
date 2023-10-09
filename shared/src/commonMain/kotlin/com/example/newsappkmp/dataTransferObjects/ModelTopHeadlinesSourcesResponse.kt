package com.example.newsappkmp.dataTransferObjects

import kotlinx.serialization.Serializable

@Serializable
data class ModelTopHeadlinesSourcesResponse(
    val status : String,
    val sources : ArrayList<ModelTopHeadlinesSource> = arrayListOf(),
    val code : String? = "",
    val message : String ? = ""
)