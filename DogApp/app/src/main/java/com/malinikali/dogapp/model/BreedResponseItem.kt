package com.malinikali.dogapp.model



data class BreedResponseItem(
    val description: String,
    val id: Int,
    val image: Image,
    val lifeSpan: String,
    val name: String,
    val origin: String
)