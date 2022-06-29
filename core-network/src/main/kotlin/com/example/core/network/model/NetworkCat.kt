package com.example.core.network.model

import com.google.gson.annotations.SerializedName

data class NetworkCat(
    val id: String,
    @SerializedName("created_at")
    val createdAt: String,
    val tags: List<String>,
    val url: String,
)
