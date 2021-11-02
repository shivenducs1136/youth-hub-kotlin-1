package com.dsckiet.youthhub.videomodel

data class VideoDataClass(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val pageInfo: PageInfo
)