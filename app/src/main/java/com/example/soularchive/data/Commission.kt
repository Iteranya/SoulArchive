package com.example.soularchive.data

data class Commission(
    val id:String,
    val title:String,
    val detail:String,
    val references:List<String>,
    val accepted:Boolean = false,
    val declined:Boolean = false
)
