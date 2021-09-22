package com.dsckiet.youthhub.searchapi

data class Item(
    val etag: String? = null,
    val id: Id? = null,
    val kind: String? = null,
    val snippet: List<Snippet>? = null
)