package com.example.chibbisapp.features.restaurants.data.api

import com.example.chibbisapp.features.restaurants.data.model.RestaurantsRes
import retrofit2.http.GET

interface RestaurantsApi {
    @GET("/api/v1/restaurants")
    suspend fun getRestaurants() : List<RestaurantsRes>
}