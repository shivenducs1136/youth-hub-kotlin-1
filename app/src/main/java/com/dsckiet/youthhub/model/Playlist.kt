package com.dsckiet.youthhub.model

data class Playlist(
    val etag: String?,
    val items: List<Item>?,
    val kind: String?,
    val pageInfo: PageInfo?
)