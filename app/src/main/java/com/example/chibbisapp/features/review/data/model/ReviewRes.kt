package com.example.chibbisapp.features.review.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewRes(
    @Json(name = "DateAdded")
    val dateAddedRes: String,
    @Json(name = "IsPositive")
    val isPositiveRes: Boolean,
    @Json(name = "Message")
    val messageRes: String,
    @Json(name = "RestaurantName")
    val restaurantNameRes: String,
    @Json(name = "UserFIO")
    val userFIORes: String
)