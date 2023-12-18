package com.example.android_dragonball.Models

data class Hero(
    val id: String,
    val name: String,
    val photo: String,
    val currentLife: Int = 100,
    val maxLife: Int = 100
)
