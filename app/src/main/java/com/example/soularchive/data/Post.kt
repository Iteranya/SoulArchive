package com.example.soularchive.data

data class Post(
    val id:String,
    val title: String,
    val artistId:String,
    val description: String,
    val media: String,
    val verified: Boolean = false,
    val likes: Int = 0,
    val favorite: Int = 0,
    val tags:List<String>
)
