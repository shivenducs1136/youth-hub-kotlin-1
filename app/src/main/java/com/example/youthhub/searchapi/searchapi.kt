package com.example.youthhub.searchapi

data class searchapi(
    val etag: String?,
    val items: List<Item>?,
    val kind: String?,
    val nextPageToken: String?,
    val pageInfo: PageInfo?,
    val regionCode: String?
)