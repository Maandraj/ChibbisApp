package com.example.chibbisapp.features.restaurants.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpecializationRes(
    @Json(name = "Name")
    val nameRes: String
)