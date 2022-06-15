package com.example.chibbisapp.features.hits.domain.model

data class Hit(
    val productDescription: String,
    val productImage: String,
    val productName: String,
    val productPrice: Int,
    val restaurantLogo: String,
    val restaurantName: String
)