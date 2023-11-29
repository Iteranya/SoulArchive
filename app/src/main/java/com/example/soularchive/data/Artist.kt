package com.example.soularchive.data

data class Artist(
    var id:String="",
    val username:String,
    val photo:String="",
    val creation: List<String> = emptyList(),//Post IDs
    val collection: List<Collection> = emptyList(),//Post IDs
    val featured: List<String> = emptyList(),//Post IDs
    val followed: List<String> = emptyList()//Artist IDs
)
