package com.example.soularchive.data

data class Post(
    val id:String? = "",
    val title: String? =null,
    val artistId:String?=null,
    val description: String?=null,
    val media: String?=null,
    val verified: Boolean? = false,
    val likes: Int? = 0,
    val favorite: Int? = 0,
    val tags:List<String>? = emptyList(),
    val uploaded: Int? = 0
)
