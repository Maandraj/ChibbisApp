package com.example.chibbisapp.features.restaurants.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RestaurantsRes(
    @Json(name = "DeliveryCost")
    val deliveryCostRes: Int,
    @Json(name = "DeliveryTime")
    val deliveryTimeRes: Int,
    @Json(name = "Logo")
    val logoRes: String,
    @Json(name = "MinCost")
    val minCostRes: Int,
    @Json(name = "Name")
    val nameRes: String,
    @Json(name = "PositiveReviews")
    val positiveReviewsRes: Int,
    @Json(name = "ReviewsCount")
    val reviewsCountRes: Int,
    @Json(name = "Specializations")
    val specializationsRes: List<SpecializationRes>
)