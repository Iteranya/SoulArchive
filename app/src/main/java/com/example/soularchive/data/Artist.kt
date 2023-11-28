package com.example.soularchive.data

data class Artist(
    var id:String,
    val username:String,
    val photo:String,
    val creation: List<String>,//Post IDs
    val collection: List<String>,//Post IDs
    val featured: List<String>,//Post IDs
    val followed: List<String>//Artist IDs
)
