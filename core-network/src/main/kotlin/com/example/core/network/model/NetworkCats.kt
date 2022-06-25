package com.example.core.network.model

data class NetworkCatsItem(
    val createdAt: String,
    val id: String,
    val tags: List<String>,
    val url: String,
)
