package com.example.chibbisapp.features.hits.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HitsRes(
    @Json(name = "ProductDescription")
    val productDescriptionRes: String,
    @Json(name = "ProductImage")
    val productImageRes: String,
    @Json(name = "ProductName")
    val productNameRes: String,
    @Json(name = "ProductPrice")
    val productPriceRes: Int,
    @Json(name = "RestaurantId")
    val restaurantIdRes: Int,
    @Json(name = "RestaurantLogo")
    val restaurantLogoRes: String,
    @Json(name = "RestaurantName")
    val restaurantNameRes: String
)