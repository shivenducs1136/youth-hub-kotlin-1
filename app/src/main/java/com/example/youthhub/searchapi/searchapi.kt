package com.example.youthhub.searchapi

data class searchapi(
    val etag: String? = null,
    val items: List<Item>? = null,
    val kind: String? = null,
    val nextPageToken: String? = null,
    val pageInfo: PageInfo? = null,
    val regionCode: String? = null
)