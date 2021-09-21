package com.example.youthhub.searchapi

data class Item(
    val etag: String?,
    val id: Id?,
    val kind: String?,
    val snippet: List<Snippet>?
)