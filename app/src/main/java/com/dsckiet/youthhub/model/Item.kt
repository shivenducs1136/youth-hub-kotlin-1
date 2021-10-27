package com.dsckiet.youthhub.model

import com.google.gson.annotations.SerializedName


data class Item(
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("snippet")
    val snippet: List<Snippet>?
){
    data class Snippet(
        val channelId: String?,
        val channelTitle: String?,
        val description: String?,
        val localized: Localized?,
        val publishedAt: String?,
        val thumbnails: Thumbnails?,
        val title: String?
    )
}