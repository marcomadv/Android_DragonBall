package com.example.android_dragonball.Models

data class Hero(
    val id: String,
    val name: String,
    val photo: String,
    var currentLife: Int = 100,
    val maxLife: Int = 100
)
