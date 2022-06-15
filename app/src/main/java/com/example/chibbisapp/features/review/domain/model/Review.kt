package com.example.chibbisapp.features.review.domain.model

data class Review(
    val dateAdded: String,
    val isPositive: Boolean,
    val message: String,
    val restaurantName: String,
    val userFIO: String
)