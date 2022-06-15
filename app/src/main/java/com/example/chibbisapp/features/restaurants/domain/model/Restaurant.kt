package com.example.chibbisapp.features.restaurants.domain.model

data class Restaurant(
    val logo: String,
    val name: String,
    val positiveReviews: Int,
    val deliveryTime: Int,
    val minCost: Int,
    val specializations: List<Specialization>
)