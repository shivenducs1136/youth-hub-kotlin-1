package com.example.youthhub.searchapi

data class Snippet(
    val channelId: String? = null,
    val channelTitle: String? = null,
    val description: String? = null,
    val liveBroadcastContent: String? = null,
    val publishTime: String? = null,
    val publishedAt: String? = null,
    val thumbnails: Thumbnails? = null,
    val title: String? = null
)